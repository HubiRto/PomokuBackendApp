package pl.pomoku.pomokubackendapp.service;

import pl.pomoku.pomokubackendapp.entity.User;

public interface UserService {
    User findByEmail(String email);
    User addUser(User user);
}
