package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.util.Optional;
import javax.annotation.Generated;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-14T11:21:04.473420Z[Etc/UTC]")
@Controller
@Slf4j
public class ParcelApiController implements ParcelApi {

    private ParcelService parcelService;

    private final NativeWebRequest request;
    @Autowired
    public ParcelApiController(ParcelService parcelService, NativeWebRequest request){
        this.parcelService = parcelService;
        this.request = request;
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(
            @Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel
    ){
        try{
            String trackingId = parcelService.submitParcel(parcel);
            String message = String.format("{ \"trackingId\" : \"%s\" }",trackingId);
            ApiUtil.setExampleResponse(request, "application/json", message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (IOException | InterruptedException e){
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId,
            @Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel
    ){
        try{
            trackingId = parcelService.transferParcel(trackingId, parcel);
            String message = String.format("{ \"trackingId\" : \"%s\" }",trackingId);
            ApiUtil.setExampleResponse(request, "application/json", message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (IOException | InterruptedException e){
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PersistenceException e) {
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId
    ) {
       try{
           String parcelInfo = parcelService.getParcelState(trackingId);
           ApiUtil.setExampleResponse(request, "application/json", parcelInfo);
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
    public ResponseEntity<Void> reportParcelDelivery(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId
    ) {
        try {
            parcelService.reportParcelDelivery(trackingId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PersistenceException e) {
            String errorMessage = String.format("{ \"error\" : \"%s\" }",e.getMessage());
            ApiUtil.setExampleResponse(request, "application/json", errorMessage);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
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
