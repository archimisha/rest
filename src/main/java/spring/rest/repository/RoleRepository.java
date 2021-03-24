package spring.rest.repository;

import spring.rest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);

    Set<Role> getRoleByNameIn(Set<String> set);
}
