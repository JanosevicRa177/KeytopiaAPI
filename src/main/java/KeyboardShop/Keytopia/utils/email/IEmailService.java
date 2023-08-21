package KeyboardShop.Keytopia.utils.email;

import KeyboardShop.Keytopia.auth.model.Buyer;
import KeyboardShop.Keytopia.sales.model.Order;

public interface IEmailService {
    void sendActivationMail(String email,String hmacToken);
    void sendAdminRegistrationEmail(String email,String password);
    void sendOrderEmail(Buyer buyer, Order order);
}
