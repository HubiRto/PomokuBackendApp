package pl.pomoku.pomokubackendapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.pomokubackendapp.entity.Role;
import pl.pomoku.pomokubackendapp.repository.RoleRepository;
import pl.pomoku.pomokubackendapp.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Role addRole(Role role) {
        return repository.save(role);
    }
}
