package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private String name;

    @Pattern(regexp = "^[A-Za-zäöüß]+[0-9a-z]+")
    private String street;

    @Pattern(regexp = "A-[0-9]{4}")
    private String postalCode;

    @Pattern(regexp = "^[A-ZÄÜÖß].[A-ZÄÜÖa-zöäüß-]+")
    private String city;

    @Pattern(regexp = "^.(Österreich|Austria).$")
    private String country;

    public RecipientEntity(String name, String street, String postalCode, String city, String country) {
        this.name = name;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
}
