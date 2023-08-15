package it.firstdocker.Dtos;

import it.firstdocker.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public record ResCustomer(
        User user,
        UserDetails userDetails
) {
}
