package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Table(name = "RECIPIENT")
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String name;

    @Pattern(regexp = "^[A-Za-zäöüß]+[0-9a-z]+")
    private String street;

    @Pattern(regexp = "A-[0-9]{4}")
    private String postalCode;

    @Pattern(regexp = "^[A-ZÄÜÖß].[A-ZÄÜÖa-zöäüß-]+")
    private String city;

    @Pattern(regexp = "^(Österreich|Austria)$")
    private String country;

    @OneToOne(mappedBy = "recipient", cascade = CascadeType.ALL)
    private ParcelEntity recipientOfParcel;

    @OneToOne(mappedBy = "sender", cascade = CascadeType.ALL)
    private ParcelEntity senderOfParcel;
}
