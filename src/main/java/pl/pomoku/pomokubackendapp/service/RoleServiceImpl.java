package pl.pomoku.pomokubackendapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.pomokubackendapp.entity.Role;
import pl.pomoku.pomokubackendapp.repository.RoleRepository;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
