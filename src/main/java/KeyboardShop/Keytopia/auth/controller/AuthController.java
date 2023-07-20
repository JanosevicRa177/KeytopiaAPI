package KeyboardShop.Keytopia.auth.controller;

import KeyboardShop.Keytopia.auth.dto.LoginDto;
import KeyboardShop.Keytopia.auth.dto.RegisterDto;
import KeyboardShop.Keytopia.auth.dto.UserDto;
import KeyboardShop.Keytopia.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping("/register/buyer")
    public ResponseEntity<Void> registerBuyer(@Valid @RequestBody final RegisterDto registerDto) {
        authService.registerBuyer(registerDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/admin")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> registerAdmin(@Valid @RequestBody final RegisterDto registerDto) {
        authService.registerAdmin(registerDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> currentUser(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        return ResponseEntity.ok(new UserDto(authService.getUserFromHeader(authHeader)));
    }
}
