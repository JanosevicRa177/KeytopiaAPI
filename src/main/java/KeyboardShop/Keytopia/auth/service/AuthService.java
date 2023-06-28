package KeyboardShop.Keytopia.auth.service;

import KeyboardShop.Keytopia.auth.dto.LoginDto;
import KeyboardShop.Keytopia.auth.dto.RegisterDto;
import KeyboardShop.Keytopia.auth.dto.UserDto;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.repository.IUserRepository;
import KeyboardShop.Keytopia.auth.security.JwtUtils;
import KeyboardShop.Keytopia.utils.excentions.ConfirmPasswordNotEqualException;
import KeyboardShop.Keytopia.utils.excentions.WrongEmailOrPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder encoder;
    private final IUserRepository userRepository;
    private final JwtUtils jwtUtils;
    
    public UserDto register(RegisterDto registerDto){
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) 
            throw new ConfirmPasswordNotEqualException();
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        User user = userRepository.save(new User(registerDto));
        return new UserDto(user);
    }

    public String login(LoginDto loginDto){
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null) throw new WrongEmailOrPasswordException();
        if(encoder.matches(loginDto.getPassword(),user.getPassword())) throw new WrongEmailOrPasswordException();
        return jwtUtils.generateAccessToken(user.getEmail());
    }
}
