package KeyboardShop.Keytopia.sales.Dto;

import KeyboardShop.Keytopia.sales.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderWithBuyerDto {
    private List<ProductDto> products;
    private String id;
    private String buyerEmail;
    private LocalDate date;
    private LocalDate deadline;
    public OrderWithBuyerDto(Order order){
        this.id = order.getId().toString();
        this.buyerEmail = order.getBuyer().getEmail();
        this.date = order.getDate();
        this.deadline = order.getDeadline();
        products = new ArrayList<>();
        order.getProducts().forEach(product -> products.add(new ProductDto(product)));
    }
}
