package at.fhtw.swen3.entities;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import ch.qos.logback.classic.spi.LoggingEventVO;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.OffsetDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

class HopArrivalEntityTest {
    @Test
    public void validationTest_NotOk() {
        HopArrivalEntity.builder()
                .code("ABC5")
                .description("Warehouse 12-27")
                .dateTime(OffsetDateTime.now());
        final LoggingEventVO entity;
        entity = build();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoggingEventVO>> violations = validator.validate(entity);
        for (ConstraintViolation<LoggingEventVO> violation : violations)
            return;
        fail("Validation should fail!");
    }

    @Test
    public void validationTest_Ok() {
        final LoggingEventVO entity = build();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoggingEventVO>> violations = validator.validate(entity);
        for (ConstraintViolation<LoggingEventVO> violation : violations)
            fail("Validation should fail!");
    }

    private LoggingEventVO build() {
        return null;
    }
}