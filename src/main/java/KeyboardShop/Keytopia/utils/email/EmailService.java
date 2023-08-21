package KeyboardShop.Keytopia.utils.email;

import KeyboardShop.Keytopia.auth.model.Buyer;
import KeyboardShop.Keytopia.sales.model.Order;
import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.utils.UtilFunctions;
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
    @Async
    public void sendOrderEmail(Buyer buyer, Order order) {
        EmailDetails details = new EmailDetails();
        details.setRecipient(buyer.getEmail());

        StringBuilder msgBody = new StringBuilder();
        msgBody.append("<div style=\"padding:auto; margin-left: auto; margin-right-auto; margin-top:32px; border: solid 4px #d1a2fa; margin-bottom:32px; width: 1140px; border-radius: 4px; display:flex;\">\n");
        msgBody.append("        <div style=\"gap: 8px; flex-grow: 1; margin-top:64px; font-size: 22px; padding-left: 16px; padding-right: 16px; align-items: center;\">\n");
        msgBody.append("            <div style=\"display: flex; gap: 4px;\">\n");
        msgBody.append("                <p style=\"font-weight: 700; margin: 0; padding-right:6px;\">Name and surname:</p>\n");
        msgBody.append("                <p style=\"margin: 0;\">").append(buyer.getName()).append(" ").append(buyer.getSurname()).append("</p>\n");
        msgBody.append("            </div>\n");
        msgBody.append("            <div style=\"display: flex;\">\n");
        msgBody.append("                <p style=\"font-weight: 700; margin: 0; padding-right:6px;\">Phone:</p>\n");
        msgBody.append("                <p style=\"margin: 0;\">").append(buyer.getPhone()).append("</p>\n");
        msgBody.append("            </div>\n");
        msgBody.append("            <div style=\"display: flex;\">\n");
        msgBody.append("                <p style=\"font-weight: 700;margin: 0; padding-right:6px; min-width: 95px;\">Address:</p>\n");
        msgBody.append("                <p style=\"margin: 0;\">").append(buyer.getAddress().getAsString()).append("</p>\n");
        msgBody.append("            </div>\n");
        msgBody.append("            <div style=\"display: flex; gap: 4px;\">\n");
        msgBody.append("                <p style=\"font-weight: 700;margin: 0; padding-right:6px; min-width: 95px;\">Delivery service :</p>\n");
        msgBody.append("                <p style=\"margin: 0;\">").append(order.getDeliveryService().getName()).append("</p>\n");
        msgBody.append("            </div>\n");
        msgBody.append("            <div style=\"display: flex;\">\n");
        msgBody.append("                <p style=\"font-weight: 700;margin: 0; padding-right:6px; min-width: 95px;\">Should be delivered by:</p>\n");
        msgBody.append("                <p style=\"margin: 0;\">").append(UtilFunctions.convertDateFormat(order.getDeadline().toString())).append("</p>\n");
        msgBody.append("            </div>\n");
        msgBody.append("            <div style=\"display: flex;\">\n");
        msgBody.append("                <p style=\"font-weight: 700;margin: 0; padding-right:6px;\">Total price:</p>\n");
        msgBody.append("                <p style=\"margin: 0;\">").append((int)order.getPrice()).append(" $</p>\n");
        msgBody.append("            </div>\n");
        msgBody.append("        </div>\n");
        msgBody.append("        <div style=\"min-width: 600px; margin-left: 16px; margin-right: 16px; margin-top: 32px; margin-bottom: 32px; border-radius: 4px; overflow:hidden\">\n");

        // Loop over products
        int index = 0;
        for (Product product : order.getProducts()) {
            msgBody.append("            <div style=\" width: calc(100% - 32px);\">\n");
            msgBody.append("                <div style=\"display: flex; height: 100px; width: 100%; background: ").append(index %2 == 0 ? "rgb(233, 216, 253)": "#d1a2fa").append("; padding: 16px;\">\n");
            msgBody.append("                    <div style=\"margin-right: auto; margin-top:auto; margin-bottom:auto; gap: 8px;\">\n");
            msgBody.append("                        <div style=\"display: flex; gap: 4px;\">\n");
            msgBody.append("                            <p style=\"font-weight: 700;margin: 0;\">Name :</p>\n");
            msgBody.append("                            <p style=\"margin: 0;\">").append(product.getName()).append("</p>\n");
            msgBody.append("                        </div>\n");
            msgBody.append("                        <div style=\"display: flex; gap: 4px;\">\n");
            msgBody.append("                            <p style=\"font-weight: 700;margin: 0;\">Id :</p>\n");
            msgBody.append("                            <p style=\"margin: 0;\">").append(product.getId().toString()).append("</p>\n");
            msgBody.append("                        </div>\n");
            msgBody.append("                        <div style=\"display: flex; gap: 4px;\">\n");
            msgBody.append("                            <p style=\"font-weight: 700;margin: 0;\">Price :</p>\n");
            msgBody.append("                            <p style=\"margin: 0;\">").append((int)product.getPrice()).append(" $</p>\n");
            msgBody.append("                        </div>\n");
            msgBody.append("                    </div>\n");
            msgBody.append("                    <img src=\"").append(product.isImageNull() ? "https://firebasestorage.googleapis.com/v0/b/keytopia-4691f.appspot.com/o/keyboard.png?alt=media&token=01d3d951-d910-4bef-b58d-747e84b417de" : product.getImage()).append("\" alt=\"Product Image\" width=\"133px\" height=\"100px\">\n");
            msgBody.append("                </div>\n");
            msgBody.append("            </div>\n");
            index++;
        }
        msgBody.append("    </div>\n");
        msgBody.append("</div>\n");

        details.setMsgBody(msgBody.toString());
        details.setSubject("Your order!");
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
