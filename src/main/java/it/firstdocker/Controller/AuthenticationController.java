package it.firstdocker.Controller;

import it.firstdocker.Dtos.AuthenticationRequest;
import it.firstdocker.Dtos.AuthenticationResponse;
import it.firstdocker.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.CookieManager;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse login = authenticationService.login(request);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, login.token())
                    .body(login);
        }
    }
