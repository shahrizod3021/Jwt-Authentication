package it.firstdocker.Service;

import it.firstdocker.Dtos.AuthenticationRequest;
import it.firstdocker.Dtos.AuthenticationResponse;
import it.firstdocker.Dtos.CustomerDto;
import it.firstdocker.Entity.User;
import it.firstdocker.Jwt.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final CustomerDtoMapper customerDtoMapper;

    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, CustomerDtoMapper customerDtoMapper, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerDtoMapper = customerDtoMapper;
        this.jwtUtil = jwtUtil;
    }

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
}
