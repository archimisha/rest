package spring.rest.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring.rest.dto.UserDto;
import spring.rest.model.User;
import spring.rest.service.UserService;

import java.util.List;

@RestController
public class AdminRestController {

    private final UserService userServiceImpl;

    public AdminRestController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("admin/allUsers")
    public ResponseEntity<List<UserDto>> userList() {
        List<UserDto> users = userServiceImpl.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("admin/userAuth")
    public ResponseEntity<UserDto> getAuthUser() {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        UserDto userDto = new UserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("admin/add")
    public ResponseEntity<UserDto> newUser(@RequestBody UserDto userDto) {
        try {
            userServiceImpl.saveUser(new User(userDto));
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("admin/edit")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        try {
            userServiceImpl.update(new User(userDto));
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin/edit/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        if (id != null && userServiceImpl.ifExists(id)) {
            UserDto userDto = new UserDto(userServiceImpl.findById(id));
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("admin/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        try {
            userServiceImpl.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
