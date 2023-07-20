package KeyboardShop.Keytopia.utils.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService{
    private final JavaMailSender javaMailSender;
    @Value
    ("${spring.mail.username}") private String sender;

    @Async
    public void sendActivationMail(String email,String hmacToken)
    {
        EmailDetails details = new EmailDetails();
        details.setRecipient(email);
        details.setMsgBody("Welcome to keytopia!<br/>" +
                "You can <a href=\"http://localhost:3000/user/activation/"+hmacToken+"\">Activate your account here!<a/><br/>");
        details.setSubject("Welcome email from your ultimate keyboard guide!");
        sendEmail(details);
    }

    @Async
    public void sendAdminRegistrationEmail(String email,String password)
    {
        EmailDetails details = new EmailDetails();
        details.setRecipient(email);
        details.setMsgBody("Welcome to keytopia!<br/>" +
                "You can temporary login password is: "+password+"</h2> <br/>"+
                "<a href=\"http://localhost:3000\">Access site here<a/><br/>");
        details.setSubject("Welcome our new admin!");
        sendEmail(details);
    }
    private void sendEmail(EmailDetails details){
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, "utf-8");
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody(), true);
            helper.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
        }
        catch (Exception ignored) {
        }
    }
}
