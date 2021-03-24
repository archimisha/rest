package spring.rest.controller.view;

import spring.rest.model.Role;
import spring.rest.model.User;
import spring.rest.service.RoleService;
import spring.rest.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String findAll() {
        return "admin/user-list-btsrp";
    }

}

