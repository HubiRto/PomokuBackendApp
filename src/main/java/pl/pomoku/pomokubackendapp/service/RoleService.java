package pl.pomoku.pomokubackendapp.service;

import pl.pomoku.pomokubackendapp.entity.Role;

public interface RoleService {
    Role findByName(String name);
    Role addRole(Role role);
}
