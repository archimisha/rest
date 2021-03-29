package spring.rest.service;

import spring.rest.dto.UserDto;
import spring.rest.model.Role;
import spring.rest.model.User;
import spring.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDto = new ArrayList<>();
        users.forEach(user -> userDto.add(new UserDto(user)));
        return userDto;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void update(User updatedUser) {
        userRepository.save(updatedUser);
    }



    public boolean ifExists(Long id) {
        return userRepository.existsById(id);
    }
}
