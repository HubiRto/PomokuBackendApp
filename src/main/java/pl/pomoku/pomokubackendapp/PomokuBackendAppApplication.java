package pl.pomoku.pomokubackendapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.pomoku.pomokubackendapp.entity.Role;
import pl.pomoku.pomokubackendapp.entity.User;
import pl.pomoku.pomokubackendapp.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class PomokuBackendAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PomokuBackendAppApplication.class, args);
    }

}
