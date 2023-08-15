package it.firstdocker.Service;

import it.firstdocker.Dtos.CustomerDto;
import it.firstdocker.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomerDtoMapper implements Function<User, CustomerDto> {
    @Override
    public CustomerDto apply(User user) {
        return new CustomerDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getAge(),
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()),
                user.getUsername()
        );
    }
}
