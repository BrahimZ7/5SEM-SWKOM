package at.fhtw.swen3.entities;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipientEntityTest {
    @Test
    public void validationTest_NotOk() {
        final RecipientEntity entity = RecipientEntity.builder()
                .name("Max")
                .street("Landstraße 27")
                .postalCode("B-2420")
                .city("Vienna")
                .country("Austria")
                .build();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(entity);
        for (ConstraintViolation<RecipientEntity> violation : violations)
            return;
        fail("Validation should fail!");
    }

    @Test
    public void validationTest_Ok() {
        final RecipientEntity entity = RecipientEntity.builder()
                .name("Max")
                .street("Landstraße 27")
                .postalCode("A-1200")
                .city("Vienna")
                .country("Austria")
                .build();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(entity);
        for (ConstraintViolation<RecipientEntity> violation : violations)
            fail("Validation should fail!");
    }
}