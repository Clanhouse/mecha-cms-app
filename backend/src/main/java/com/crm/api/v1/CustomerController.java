package com.crm.api.v1;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.request.PageRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ApiOperation(value = "Finds all customers paginated", notes = "Add param \"page\" and/or \"size\""
            + " to specify page no. and size of each page.")
    public ResponseEntity<Page<CustomerResponse>> getCustomersPaginated(@Valid final PageRequest pageRequest) {
        return ResponseEntity.ok(customerService.getCustomersPaginated(pageRequest.getPage(), pageRequest.getSize()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds customer by id")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable final Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    @ApiOperation(value = "Endpoint is used to create a new customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@ApiParam(name = "customerRequest", type = "CustomerRequest", value = "Object represents the customer " +
            "that will be added to the database", required = true) @Valid @RequestBody final CustomerRequest customerRequest) {
        customerService.addCustomer(customerRequest);
    }
}
