package it.firstdocker.Service;

import it.firstdocker.Dtos.CustomerDto;
import it.firstdocker.Dtos.CustomerRegistrationRequest;
import it.firstdocker.Dtos.ResCustomer;
import it.firstdocker.Entity.Gender;
import it.firstdocker.Entity.Role;
import it.firstdocker.Exceptions.DuplicateDataException;
import it.firstdocker.Entity.User;
import it.firstdocker.Exceptions.ResourceNotfoundExceptions;
import it.firstdocker.Full.CustomerFull;
import it.firstdocker.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Qualifier(value = "jpa")
    private final CustomerFull customerFull;

    private final CustomerDtoMapper customerDtoMapper;

    private final PasswordEncoder passwordEncoder;

    private final CustomerRepository customerRepository;

    private final UserDetailsService userDetailsService;

    public List<ResCustomer> getCustomerDtos() {
        List<User> all = customerRepository.findAll();
        List<ResCustomer> resCustomers = new ArrayList<>();
        for (User user : all) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            ResCustomer resCustomer = new ResCustomer(
                    user,
                    userDetails
            );
            resCustomers.add(resCustomer);
        }
        return resCustomers;
    }

    public CustomerDto getCustomer(Long id) {
        return customerFull.selectCustomerById(id)
                .map(customerDtoMapper)
                .orElseThrow(() -> new ResourceNotfoundExceptions(
                        "customer with id [%s] not found".formatted(id)
                ));
    }

//    public User addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
//        String email = customerRegistrationRequest.email();
//        if (customerFull.existsCustomerWithEmail(email)) {
//            throw new DuplicateDataException("usbu email oldindan mavjud");
//        }
//        User user = User.builder()
//                .name(customerRegistrationRequest.name())
//                .age(customerRegistrationRequest.age())
//                .gender(customerRegistrationRequest.gender() == 1 ? Gender.MALE : Gender.FEMALE)
//                .email(customerRegistrationRequest.email())
//                .password(passwordEncoder.encode(customerRegistrationRequest.password()))
//                .role(Role.USER)
//                .build();
//        customerFull.insertCustomer(user);
//        return user;
//    }
}
