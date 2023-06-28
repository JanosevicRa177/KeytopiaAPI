package KeyboardShop.Keytopia.auth.service;

import KeyboardShop.Keytopia.auth.dto.LoginDto;
import KeyboardShop.Keytopia.auth.dto.RegisterDto;
import KeyboardShop.Keytopia.auth.dto.UserDto;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.repository.IUserRepository;
import KeyboardShop.Keytopia.auth.security.JwtUtils;
import KeyboardShop.Keytopia.utils.excentions.ConfirmPasswordNotEqualException;
import KeyboardShop.Keytopia.utils.excentions.UserAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.WrongEmailOrPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder encoder;
    private final IUserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    
    public UserDto register(RegisterDto registerDto){
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword()))
            throw new ConfirmPasswordNotEqualException();
        if(userRepository.findByEmail(registerDto.getEmail()) != null) throw new UserAlreadyExistsException();
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        User user = userRepository.save(new User(registerDto));
        return new UserDto(user);
    }

    public String login(LoginDto loginDto){
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null) throw new WrongEmailOrPasswordException();
        Authentication authStrategy = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authStrategy);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateAccessToken(user.getEmail());
    }
}
