package KeyboardShop.Keytopia.auth.service;

import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.repository.IUserRepository;
import KeyboardShop.Keytopia.auth.security.JwtUtils;
import KeyboardShop.Keytopia.utils.excentions.tokenExceptions.InvalidTokenClaimsException;
import KeyboardShop.Keytopia.utils.excentions.tokenExceptions.TokenExpiredException;
import KeyboardShop.Keytopia.utils.excentions.tokenExceptions.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtUtils jwtUtils;
    private final IUserRepository userRepository;

    public User findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public void activateAccount(String activationToken){
        Claims registerClaims;
        try{
            jwtUtils.validateActivationToken(activationToken);
            registerClaims = jwtUtils.getClaimsFromActivationToken(activationToken);
        }catch(final ExpiredJwtException e){
            throw new TokenExpiredException("Your account link expired.");
        }catch(final Exception e){
            throw new TokenInvalidException("Your account link invalid");
        }
        User user = findByEmail(registerClaims.getSubject());
        if(user == null) throw new InvalidTokenClaimsException();
        
        user.setActivated(true);
        userRepository.save(user);
    }
}
