package at.fhtw.swen3.controller.rest;
import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehouseApiControllerTest {

    @Test
    void mapErrorDTOToEntity() {
        Error errorDTO = new Error();
        errorDTO.errorMessage("This is an error");

        ErrorEntity errorEntity = ErrorMapper.INSTANCE.dtoToEntity(errorDTO);

        assertEquals(errorEntity.getErrorMessage(), errorDTO.getErrorMessage());
    }

}
