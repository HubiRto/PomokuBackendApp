package pl.pomoku.pomokubackendapp.service;

import pl.pomoku.pomokubackendapp.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByEmail(String email);
    User saveUser(User user);
}
