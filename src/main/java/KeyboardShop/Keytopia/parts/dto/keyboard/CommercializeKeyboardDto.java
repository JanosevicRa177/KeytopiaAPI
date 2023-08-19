package KeyboardShop.Keytopia.parts.dto.keyboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CommercializeKeyboardDto {
    private String newName;
    private MultipartFile image;
}
