package spring.rest.service;

import spring.rest.model.Role;
import spring.rest.model.User;
import spring.rest.repository.RoleRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void add(Role role) {
        roleRepository.save(role);
    }

    public void edit(Role role) {
        roleRepository.save(role);
    }

    public Role getById(Long id) {
        Role role = null;
        Optional<Role> opt = roleRepository.findById(id);
        if (opt.isPresent()) {
            role = opt.get();
        }
        return role;
    }

    public Role getByName(String name) throws NotFoundException {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new NotFoundException(name);
        }
        return role;
    }

    public Set<Role> getRoleSet(Set<String> roles) {
        return new HashSet<>(roleRepository.getRoleByNameIn(roles));
    }
    public  String getViewRoleSet(User user) {
        return user.getRoles()
                .stream()
                .map(role -> role.getName().substring(5))
                .collect(Collectors.joining(", "));
    }
}
