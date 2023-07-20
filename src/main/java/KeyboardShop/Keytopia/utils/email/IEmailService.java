package KeyboardShop.Keytopia.utils.email;

public interface IEmailService {
    void sendActivationMail(String email,String hmacToken);
    void sendAdminRegistrationEmail(String email,String password);
}
