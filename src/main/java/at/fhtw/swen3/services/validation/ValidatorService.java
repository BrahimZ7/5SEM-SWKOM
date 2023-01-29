package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.services.exception.BLValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
@Slf4j
public class ValidatorService {
    static ValidatorFactory getValidatorFactory() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory;
    }

    static javax.validation.Validator getValidator() {
        return getValidatorFactory().getValidator();
    }

    public <T> void validate(T o) {
        javax.validation.Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        for (ConstraintViolation<T> violation : violations) {
            log.error(violation.getInvalidValue() + " " + violation.getMessage());
        }

        if (!violations.isEmpty()) {
            throw new BLValidationException("Validation failed");
        }
    }
}
