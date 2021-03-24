package spring.rest.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.rest.dto.UserDto;
import spring.rest.model.User;

@RestController
@RequestMapping("users/**")
public class UserRestController {

    @GetMapping("authUser")
    public ResponseEntity<UserDto> getAuthUser() {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        UserDto dtoUser = new UserDto(user);
        return new ResponseEntity<>(dtoUser, HttpStatus.OK);
    }
}
