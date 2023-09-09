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
    private PartItemDto caseDto;
    private PartItemDto cableDto;
    private PartItemDto keycapSetDto;
    private PartItemDto switchSetDto;
    private PartItemDto pcbDto;
    private PartItemDto plateDto;
    private PartItemDto stabilizersDto;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private boolean switchesLubed;
    private boolean assembled;
    private boolean generatedByAdmin;
    
    public KeyboardDto(Keyboard keyboard,boolean isAdmin){
        this.name = keyboard.getName();
        this.caseDto = new PartItemDto(keyboard.getACase());
        this.pcbDto = new PartItemDto(keyboard.getPcb());
        this.stabilizersDto = new PartItemDto(keyboard.getStabilizers());
        if(keyboard.getCable() != null)
            this.cableDto = new PartItemDto(keyboard.getCable());
        if(keyboard.getKeycapSet() != null)
            this.keycapSetDto = new PartItemDto(keyboard.getKeycapSet());
        if(keyboard.getPlate() != null)
            this.plateDto = new PartItemDto(keyboard.getPlate());
        if(keyboard.getSwitchSet() != null)
            this.switchSetDto = new PartItemDto(keyboard.getSwitchSet());
        this.price = keyboard.getPrice();
        this.imageUrl = keyboard.getImageUrl();
        if(isAdmin)
            this.quantity = keyboard.getQuantity();
        this.assembled = keyboard.isAssembled();
        this.switchesLubed = keyboard.isSwitchesLubed();
        this.generatedByAdmin = keyboard.isGeneratedByAdmin();
    }   
    
}
