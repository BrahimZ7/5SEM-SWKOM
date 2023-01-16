package at.fhtw.swen3.controller.rest;

//Cannot resolve symbol 'HopService'
//import at.fhtw.swen3.services.HopService;

import at.fhtw.swen3.services.dto.Hop;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.persistence.PersistenceException;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-14T11:21:04.473420Z[Etc/UTC]")
@Controller
@Slf4j
public class WarehouseApiController implements WarehouseApi {

    private final NativeWebRequest request;
    private HopService hopService;
    @Autowired
    public WarehouseApiController(HopService hopService,NativeWebRequest request) {
        this.hopService = hopService;
        this.request = request;
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(
            @Parameter(name = "code", description = "", required = true) @PathVariable("code") String code
    ) {
        try{
            String hopEntityJson = hopService.getHopEntityByCode(code);
            ApiUtil.setExampleResponse(request, "application/json", hopEntityJson);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PersistenceException e){
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
