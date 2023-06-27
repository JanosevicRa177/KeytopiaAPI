package KeyboardShop.Keytopia.auth.controller;

import KeyboardShop.Keytopia.auth.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody final LoginDto loginRequest) {
        String data = "prosao!";
        return ResponseEntity.ok(data);
    }
}
