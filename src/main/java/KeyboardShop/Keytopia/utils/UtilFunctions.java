package KeyboardShop.Keytopia.utils;

import KeyboardShop.Keytopia.auth.model.Role;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.security.JwtUtils;
import KeyboardShop.Keytopia.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UtilFunctions {
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    public int getMinQuantity(String authHeader) {
        int minQuantity = 1;
        if(authHeader != null && jwtUtils.validateAuthToken(authHeader.substring(7))){
            User user = authService.getUserFromHeader(authHeader);
            if(user.getRole() == Role.ADMIN){
                minQuantity = 0;
            }
        }
        return minQuantity;
    }
    public static String convertDateFormat(String inputDate) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, inputFormatter);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(outputFormatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
