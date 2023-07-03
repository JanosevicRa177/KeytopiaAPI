package KeyboardShop.Keytopia.auth.controller;

import KeyboardShop.Keytopia.auth.dto.LoginDto;
import KeyboardShop.Keytopia.auth.dto.RegisterDto;
import KeyboardShop.Keytopia.auth.dto.UserDto;
import KeyboardShop.Keytopia.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody final LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }
    @PostMapping("/register")
    public ResponseEntity<Void> Register(@Valid @RequestBody final RegisterDto registerDto) {
        authService.register(registerDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> CurrentUser(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        return ResponseEntity.ok(new UserDto(authService.getUserFromHeader(authHeader)));
    }
}
