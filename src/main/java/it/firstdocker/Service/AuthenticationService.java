package it.firstdocker.Service;

import it.firstdocker.Dtos.*;
import it.firstdocker.Entity.Gender;
import it.firstdocker.Entity.Role;
import it.firstdocker.Entity.User;
import it.firstdocker.Exceptions.DuplicateDataException;
import it.firstdocker.Exceptions.ResourceNotfoundExceptions;
import it.firstdocker.Jwt.JWTUtil;
import it.firstdocker.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final CustomerDtoMapper customerDtoMapper;

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;


    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        User principal = (User) authentication.getPrincipal();
        CustomerDto customerDto = customerDtoMapper.apply(principal);
        String token = jwtUtil.issueToken(customerDto.username(), customerDto.roles());
        return new AuthenticationResponse(token, customerDto);
    }

    public void register(CustomerRegistrationRequest customerRegistrationRequest) {
        String email = customerRegistrationRequest.email();
        if (customerRepository.existsUserByEmail(email)) {
            throw new DuplicateDataException("usbu email oldindan mavjud");
        }
        User user = User.builder()
                .name(customerRegistrationRequest.name())
                .age(customerRegistrationRequest.age())
                .gender(customerRegistrationRequest.gender() == 1 ? Gender.MALE : Gender.FEMALE)
                .email(customerRegistrationRequest.email())
                .password(passwordEncoder.encode(customerRegistrationRequest.password()))
                .role(Role.USER)
                .build();
        customerRepository.save(user);
    }

    public ApiResponse<?> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = customerRepository.findUserByEmail(request.username()).orElseThrow(() -> new ResourceNotfoundExceptions("topilmadi"));
        String token = jwtUtil.issueToken(user.getUsername(), user.getRole().name());
        return new ApiResponse<>(token, true, 200);
    }
}
