package KeyboardShop.Keytopia.auth.controller;

import KeyboardShop.Keytopia.auth.dto.LoginDto;
import KeyboardShop.Keytopia.auth.dto.RegisterDto;
import KeyboardShop.Keytopia.auth.dto.UserDto;
import KeyboardShop.Keytopia.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<UserDto> Register(@Valid @RequestBody final RegisterDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }
    @GetMapping("/pao")
    public ResponseEntity<String> Pao() {
        String data = "Pao!";
        return ResponseEntity.ok(data);
    }
}
