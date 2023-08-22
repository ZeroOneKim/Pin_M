package byk.pinM.controller.Account;

import byk.pinM.response.SignUpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        return "account/signUp";
    }

    @PostMapping("signUp")
    public String signUpResponse(@ModelAttribute SignUpResponse signUpResponse, Errors errors) { //Error Code 작성 필요.
        if(errors.hasErrors()) {
            System.out.println("failed");
            return "account/signUp";  //에러처리 예정
        }
        System.out.println(signUpResponse);
        System.out.println("Success");
        return "redirect:/";
    }


    @GetMapping("/asd")
    public String testPage(Model model) {
        return "/asd";
    }
}
