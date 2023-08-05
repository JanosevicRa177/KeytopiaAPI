package KeyboardShop.Keytopia.auth.service;

import KeyboardShop.Keytopia.auth.dto.LoginDto;
import KeyboardShop.Keytopia.auth.dto.RegisterAdminDto;
import KeyboardShop.Keytopia.auth.dto.RegisterBuyerDto;
import KeyboardShop.Keytopia.auth.model.Admin;
import KeyboardShop.Keytopia.auth.model.Buyer;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.repository.IAdminRepository;
import KeyboardShop.Keytopia.auth.repository.IBuyerRepository;
import KeyboardShop.Keytopia.auth.repository.IUserRepository;
import KeyboardShop.Keytopia.auth.security.JwtUtils;
import KeyboardShop.Keytopia.utils.email.IEmailService;
import KeyboardShop.Keytopia.utils.excentions.authExceptions.ConfirmPasswordNotEqualException;
import KeyboardShop.Keytopia.utils.excentions.authExceptions.UserAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.authExceptions.UserNotActivatedException;
import KeyboardShop.Keytopia.utils.excentions.authExceptions.WrongEmailOrPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder encoder;
    private final IUserRepository userRepository;
    private final IBuyerRepository buyerRepository;
    private final IAdminRepository adminRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final IEmailService emailService;
    
    public void registerBuyer(RegisterBuyerDto registerDto){
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword()))
            throw new ConfirmPasswordNotEqualException();
        if(userRepository.findByEmail(registerDto.getEmail()) != null) throw new UserAlreadyExistsException();
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        buyerRepository.save(new Buyer(registerDto));
        
        String activationToken = jwtUtils.generateRegisterToken(registerDto.getEmail());
        emailService.sendActivationMail(registerDto.getEmail(),activationToken);
    }

    public void registerAdmin(RegisterAdminDto registerDto){
        if(userRepository.findByEmail(registerDto.getEmail()) != null) throw new UserAlreadyExistsException();
        Admin admin = new Admin(registerDto);
        String password = generateRandomPassword();
        admin.setPassword(encoder.encode(password));
        adminRepository.save(admin);
        
        emailService.sendAdminRegistrationEmail(registerDto.getEmail(),password);
    }

    public String login(LoginDto loginDto){
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null) throw new WrongEmailOrPasswordException();
        if(!user.isActivated()) throw new UserNotActivatedException();
        Authentication authStrategy = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authStrategy);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateAccessToken(user.getEmail(),user.getRole().toString());
    }

    public User getUserFromHeader(String header){
        String token = getTokenFromAuthHeader(header);
        String email = jwtUtils.getEmailFromAuthToken(token);
        User user = userRepository.findByEmail(email);
        if (user == null) throw new WrongEmailOrPasswordException();
        return user;
    }
    private String getTokenFromAuthHeader(String header) {
        if(header == null) return null;
        String[] chunks = header.split(" ");
        if(chunks.length < 2) return null;
        return chunks[1];
    }
    
    private String generateRandomPassword() {
        int leftLimit = 97; //a
        int rightLimit = 122; //z
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
