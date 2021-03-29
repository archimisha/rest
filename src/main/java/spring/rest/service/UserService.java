package spring.rest.service;

import spring.rest.dto.UserDto;
import spring.rest.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<UserDto> findAll();
    void saveUser(User user);
    void deleteById(Long id);
    void update(User updatedUser);
    boolean ifExists(Long id);
}
