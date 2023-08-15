package it.firstdocker.Controller;

import it.firstdocker.Dtos.CustomerDto;
import it.firstdocker.Dtos.CustomerRegistrationRequest;
import it.firstdocker.Dtos.ResCustomer;
import it.firstdocker.Entity.RoleName;
import it.firstdocker.Entity.User;
import it.firstdocker.Jwt.JWTUtil;
import it.firstdocker.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final JWTUtil jwtUtil;


    @GetMapping
    public List<ResCustomer> getCustomer() {
        return customerService.getCustomerDtos();
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        User user = customerService.addCustomer(request);
        String jwtToken = jwtUtil.issueToken(request.email(), RoleName.USER.name());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }
}
