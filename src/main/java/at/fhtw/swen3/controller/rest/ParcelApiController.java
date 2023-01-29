package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.services.ParcelService;
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

    private final ParcelService parcelService;
    private final NativeWebRequest request;

    @Autowired
    public ParcelApiController(NativeWebRequest request, ParcelService parcelService) {
        this.request = request;
        this.parcelService = parcelService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(@RequestBody Parcel parcel) {
        var newParcelInfo = parcelService.submitParcel(parcel);
        return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(@PathVariable("trackingId") String trackingId, @RequestBody Parcel parcel) {
        var newParcelInfo = parcelService.transitionParcel(parcel, trackingId);
        return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(@PathVariable("trackingId") String trackingId) {
        var trackingInformation = parcelService.trackParcel(trackingId);
        return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(@PathVariable("trackingId") String trackingId) {
        parcelService.reportParcelDelivery(trackingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}