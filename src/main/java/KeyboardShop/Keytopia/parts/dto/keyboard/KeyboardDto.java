package KeyboardShop.Keytopia.parts.dto.keyboard;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeyboardDto {
    private String name;
    private CaseDto caseDto;
    private CableDto cableDto;
    private KeycapSetDto keycapSetDto;
    private SwitchSetDto switchSetDto;
    private PCBDto pcbDto;
    private PlateDto plateDto;
    private StabilizersDto stabilizersDto;
    
    public KeyboardDto(Keyboard keyboard){
        this.name = keyboard.getName();
        this.caseDto = new CaseDto(keyboard.getACase());
        this.cableDto = new CableDto(keyboard.getCable());
        this.pcbDto = new PCBDto(keyboard.getPcb());
        this.keycapSetDto = new KeycapSetDto(keyboard.getKeycapSet());
        this.plateDto = new PlateDto(keyboard.getPlate());
        this.stabilizersDto = new StabilizersDto(keyboard.getStabilizer());
        this.switchSetDto = new SwitchSetDto(keyboard.getSwitchSet());
    }
    
}
