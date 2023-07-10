package pl.pomoku.pomokubackendapp.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pomoku.pomokubackendapp.request.LoginRequest;
import pl.pomoku.pomokubackendapp.request.RegisterRequest;
import pl.pomoku.pomokubackendapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        authService.register(request);
        return ResponseEntity.ok().body("Stworzono konto");
    }

    @PostMapping("/login")
    public ResponseEntity<?> register(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
