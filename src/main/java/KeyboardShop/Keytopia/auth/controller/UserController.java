package KeyboardShop.Keytopia.auth.controller;

import KeyboardShop.Keytopia.auth.dto.PasswordChangeDto;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.service.AuthService;
import KeyboardShop.Keytopia.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    
    @PatchMapping("/activate/{activationToken}")
    public ResponseEntity<Void> activateAccount(@PathVariable String activationToken) {
        userService.activateAccount(activationToken);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeDto passwordChangeDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        final User user = authService.getUserFromHeader(authHeader);
        userService.changePassword(user, passwordChangeDto);
        return ResponseEntity.ok().build();
    }
}
