package byk.pinM.controller.Account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        return "account/signUp";
    }
}
