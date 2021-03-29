package spring.rest.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String findAll() {
        return "admin/user-list-btsrp";
    }

}

