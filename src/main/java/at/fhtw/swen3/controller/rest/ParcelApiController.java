package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-17T07:40:13.382299Z[Etc/UTC]")
@Controller
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;

    @Autowired
    public ParcelApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(
            @Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel
    ) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId,
            @Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel
    ) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}