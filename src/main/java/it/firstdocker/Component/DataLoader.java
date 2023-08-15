package it.firstdocker.Component;

import it.firstdocker.Entity.Gender;
import it.firstdocker.Entity.RoleName;
import it.firstdocker.Entity.User;
import it.firstdocker.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    private final PasswordEncoder passwordEncoder;


    private final CustomerRepository customerRepository;

    private static final Logger LOGGER  = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {
        if (init.equals("create") || init.equals("create-drop")){
            User user = new User(
                    "shaxrizod",
                    "shaxrizodmirzaaliyev@gmail.com",
                    passwordEncoder.encode("q12w3e4r"),
                    17,
                    Gender.MALE,
                    RoleName.ADMIN.name()

            );
            customerRepository.save(user);
        }
    }
}
