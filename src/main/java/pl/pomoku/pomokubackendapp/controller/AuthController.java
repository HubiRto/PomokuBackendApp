package pl.pomoku.pomokubackendapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.pomoku.pomokubackendapp.entity.Role;
import pl.pomoku.pomokubackendapp.entity.User;
import pl.pomoku.pomokubackendapp.request.LoginRequest;
import pl.pomoku.pomokubackendapp.request.RegisterRequest;
import pl.pomoku.pomokubackendapp.service.impl.RoleServiceImpl;
import pl.pomoku.pomokubackendapp.service.impl.UserServiceImpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleServiceImpl roleService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userService.findByEmail(request.email()) != null) {
            return new ResponseEntity<>("Ten email jest już w uźyciu.", HttpStatus.BAD_REQUEST);
        }

        String checkedPassword = checkPasswordStrength(request.password());
        if(!checkedPassword.isBlank() || !checkedPassword.isEmpty()){
            return new ResponseEntity<>(checkedPassword, HttpStatus.BAD_REQUEST);
        }

        Role userRole = roleService.findByName("USER");

        if (userRole == null) {
            userRole = roleService.addRole(Role.builder().name("USER").build());
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(new HashSet<>(Collections.singletonList(userRole)))
                .firsName(request.firstName())
                .lastName(request.lastName())
                .build();

        userService.addUser(user);
        return new ResponseEntity<>("Pomyślnie zarejestrowano użytkownika. Proszę się zalogować.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        User user = userService.findByEmail(request.email());
        if(user == null || !passwordEncoder.matches(request.password(), user.getPassword())){
            return new ResponseEntity<>("Nieprawidłowy email lub hasło.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Zalogowałeś się na konto.", HttpStatus.OK);
    }

    private String checkPasswordStrength(String password){
        StringBuilder sb = new StringBuilder();
        if(password.length() < 8){
            sb.append("Minimalna długość hasła to 8 znaków\n");
        }
        if (!(Pattern.matches(".*[a-z].*", password)
                && Pattern.matches(".*[A-Z].*", password)
                && Pattern.matches(".*[0-9].*", password))) {
            sb.append("Hasło powinno byc alfanumeryczne\n");
        }
        if(!(Pattern.matches(".*[!@#$%^&*()].*", password))){
            sb.append("Hasło powinno zawierać znaki specjalne\n");
        }
        return sb.toString();
    }
}
