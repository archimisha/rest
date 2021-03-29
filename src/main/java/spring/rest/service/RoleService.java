package spring.rest.service;

import javassist.NotFoundException;
import spring.rest.model.Role;
import spring.rest.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getAllRoles();
    void add(Role role);
    void edit(Role role);
    Role getById(Long id);
    Role getByName(String name) throws NotFoundException;
    Set<Role> getRoleSet(Set<String> roles);
    String getViewRoleSet(User user);
}
