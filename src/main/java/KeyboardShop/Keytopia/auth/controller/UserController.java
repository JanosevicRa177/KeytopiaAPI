package KeyboardShop.Keytopia.auth.controller;

import KeyboardShop.Keytopia.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @PatchMapping("/activate/{activationToken}")
    public ResponseEntity<Void> activateAccount(@PathVariable String activationToken) {
        userService.activateAccount(activationToken);
        return ResponseEntity.ok().build();
    }
}
