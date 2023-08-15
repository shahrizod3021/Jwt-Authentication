package it.firstdocker.Controller;

import it.firstdocker.Dtos.ApiResponse;
import it.firstdocker.Dtos.AuthenticationRequest;
import it.firstdocker.Dtos.AuthenticationResponse;
import it.firstdocker.Dtos.CustomerRegistrationRequest;
import it.firstdocker.Entity.Role;
import it.firstdocker.Entity.User;
import it.firstdocker.Jwt.JWTUtil;
import it.firstdocker.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.CookieManager;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse login = authenticationService.login(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, login.token())
                .body(login);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRegistrationRequest request) {
        authenticationService.register(request);
        String jwtToken = jwtUtil.issueToken(request.email(), Role.USER.name());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @PostMapping("/authenticate")
    public HttpEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        ApiResponse<?> authenticate = authenticationService.authenticate(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, authenticate.message())
                .body(authenticate);
    }
}


