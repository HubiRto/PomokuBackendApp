package pl.pomoku.pomokubackendapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.pomokubackendapp.entity.User;
import pl.pomoku.pomokubackendapp.repository.UserRepository;
import pl.pomoku.pomokubackendapp.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }
}
