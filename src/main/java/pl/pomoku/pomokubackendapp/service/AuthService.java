package pl.pomoku.pomokubackendapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pomoku.pomokubackendapp.entity.Role;
import pl.pomoku.pomokubackendapp.exception.AppException;
import pl.pomoku.pomokubackendapp.mapper.UserMapper;
import pl.pomoku.pomokubackendapp.request.LoginRequest;
import pl.pomoku.pomokubackendapp.request.RegisterRequest;
import pl.pomoku.pomokubackendapp.response.LoginResponse;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public void register(RegisterRequest request) {

        if(userService.getUserByEmail(request.email()).isPresent()) {
            throw new AppException("Użytkownik o adresie email " + request.email() + " już jest zarejestrowany.", HttpStatus.BAD_REQUEST);
        }

        var user = userMapper.registerRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        Optional<Role> optionalRole = roleService.getRoleByName("USER");
        Role role = optionalRole.orElseGet(() -> roleService.saveRole(new Role(null, "USER")));

        user.setRoles(Collections.singleton(role));
        userService.saveUser(user);
    }

    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userService.getUserByEmail(request.email()).orElseThrow();
        return new LoginResponse(request.email(), jwtService.generateToken(user));
    }
}
