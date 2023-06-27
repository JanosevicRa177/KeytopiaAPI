package KeyboardShop.Keytopia.shared.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Address")
public class Address {
    @Id
    @Column(name="idAddress")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Street")
    private String street;
    @Column(name="StreetNumber")
    private String streetNumber;
    @Column(name="City")
    private String city;
    @Column(name="ZipCode")
    private String zipCode;
    @Column(name="Country")
    private String country;
}
