package at.fhtw.swen3.controller.rest;
import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParcelApiControllerTest {

    @Test
    void mapErrorEntityToDTO() {
        ErrorEntity errorEntity = new ErrorEntity("This is an error");

        Error errorDTO = ErrorMapper.INSTANCE.entityToDto(errorEntity);

        assertEquals(errorEntity.getErrorMessage(), errorDTO.getErrorMessage());
    }
}
