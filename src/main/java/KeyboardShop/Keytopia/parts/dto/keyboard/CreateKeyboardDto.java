package KeyboardShop.Keytopia.parts.dto.keyboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CreateKeyboardDto {
    private String name;
    private String pcb;
    private String caseEntity;
    private String plate;
    private String keycapSet;
    private String cable;
    private String stabilizers;
    private String switchSet;
    private MultipartFile image;
    private Boolean isAssembled;
    private Boolean switchesLubed;
}
