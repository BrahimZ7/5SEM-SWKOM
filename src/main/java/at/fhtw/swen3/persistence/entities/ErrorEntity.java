package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ErrorEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private String errorMessage;

    public ErrorEntity(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
