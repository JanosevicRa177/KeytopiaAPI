package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.auth.model.Role;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.security.JwtUtils;
import KeyboardShop.Keytopia.auth.service.AuthService;
import KeyboardShop.Keytopia.parts.dto.keyboard.CreateKeyboardDto;
import KeyboardShop.Keytopia.parts.dto.keyboard.KeyboardDto;
import KeyboardShop.Keytopia.parts.dto.part.PartItemDto;
import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import KeyboardShop.Keytopia.parts.service.KeyboardService;
import KeyboardShop.Keytopia.utils.model.SortDirection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/keyboard")
@RequiredArgsConstructor
public class KeyboardController {
    private final KeyboardService keyboardService;
    private final AuthService authService;
    private final JwtUtils jwtUtils;
    
    @PostMapping(value ="/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeyboardAdmin(@ModelAttribute final CreateKeyboardDto keyboardDto) {
        keyboardService.createKeyboard(keyboardDto,true);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value ="/buyer")
    @PreAuthorize("isAuthenticated() and hasAuthority('BUYER')")
    public ResponseEntity<Void> createKeyboardBuyer(@RequestBody final CreateKeyboardDto keyboardDto) {
        keyboardService.createKeyboard(keyboardDto,false);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteKeyboard(@PathVariable String name) {
        keyboardService.deleteKeyboard(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("commercialize/{name}")
    public ResponseEntity<Void> commercializeKeyboard(@PathVariable String name, @RequestBody final String newName) {
        keyboardService.commercializeKeyboard(name, newName);
        return ResponseEntity.ok().build();
    }

    @PutMapping("make/{name}/{quantity}")
    public ResponseEntity<Void> makeKeyboard(@PathVariable String name, @PathVariable int quantity) {
        keyboardService.makeKeyboard(name, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("page")
    public ResponseEntity<Page<PartItemDto>> findAllKeyboards(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) String name, 
                              @RequestParam SortDirection sortDirection, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader, 
                              @RequestParam(required = false) Boolean generatedByAdmin) {
        String value = "price";
        Boolean adminGenerated = true;
        int minQuantity = 1;
        if(authHeader != null && jwtUtils.validateAuthToken(authHeader.substring(7))){
            User user = authService.getUserFromHeader(authHeader);
            if(user.getRole() == Role.ADMIN){
                minQuantity = 0;
                value = "quantity";
                adminGenerated = generatedByAdmin;
            }
        }
        Page<Keyboard> keyboardPage = keyboardService.findAllKeyboards( name, minQuantity, adminGenerated, pageSize, pageNumber, sortDirection,value);
        List<PartItemDto> keyboardDtos = new ArrayList<>();
        keyboardPage.getContent().forEach((part)-> keyboardDtos.add(new PartItemDto(part)));
        Page<PartItemDto> keyboardPageDto = new PageImpl<>(keyboardDtos, keyboardPage.getPageable(),keyboardPage.getTotalElements());
        return ResponseEntity.ok(keyboardPageDto);
    }

    @GetMapping("/{name}")
    public ResponseEntity<KeyboardDto> findOneKeyboard(@PathVariable String name) {
        Keyboard keyboard = keyboardService.findOneKeyboard(name);
        return ResponseEntity.ok(new KeyboardDto(keyboard));
    }
}
