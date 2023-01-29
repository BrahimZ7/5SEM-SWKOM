package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.WarehouseApi;


import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-17T07:40:13.382299Z[Etc/UTC]")
@Controller
public class WarehouseApiController implements WarehouseApi {

    private final NativeWebRequest request;
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseApiController(NativeWebRequest request, WarehouseService warehouseService) {
        this.request = request;
        this.warehouseService = warehouseService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(@Parameter(name = "code", description = "",
            required = true) @PathVariable("code") String code) {
        var warehouse = warehouseService.getWarehouse(code);
        return new ResponseEntity<Hop>(warehouse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses() {
        var warehouses = warehouseService.exportWarehouses();
        return new ResponseEntity<Warehouse>(warehouses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        warehouseService.importWarehouses(warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}