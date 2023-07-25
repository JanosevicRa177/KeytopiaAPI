package KeyboardShop.Keytopia.shared.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Address")
public class Address {
    @Id
    @Column(name="id_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="street")
    private String street;
    @Column(name="street_number")
    private String streetNumber;
    @Column(name="city")
    private String city;
    @Column(name="zip_code")
    private String zipCode;
    @Column(name="country")
    private String country;
}
