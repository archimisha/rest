package spring.rest.controller.view;

import spring.rest.model.User;
import spring.rest.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping()
    public String findAll() {
        return "user/user-list-btsrp";
    }

}
