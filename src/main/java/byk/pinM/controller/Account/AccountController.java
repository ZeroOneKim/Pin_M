package byk.pinM.controller.Account;

import byk.pinM.entity.Account.SignUpResponse;
import byk.pinM.service.SignUpResponseValid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    private SignUpResponseValid signUpResponseValid;
    public void SignUpResponseValidMethod (SignUpResponseValid signUpResponseValid) {
        this.signUpResponseValid = signUpResponseValid;
    }

    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("signUpResponse", new SignUpResponse());
        return "account/signUp";
    }

    @PostMapping("signUp")
    public String signUpResponse(@ModelAttribute SignUpResponse signUpResponse, Errors errors) { //Error Code 작성 필요.
        /*SignUpResponseValidMethod(signUpResponseValid);
        signUpResponseValid.validate(signUpResponse, errors);*/
        if(errors.hasErrors()) {
            System.out.println("failed");
            System.out.println("ERROR STATUS(값 체크) : " + errors.hasErrors());
            return "account/signUp";  //에러처리 예정
        }

        System.out.println("ERROR STATUS(값 체크) : " + errors.hasErrors());

        System.out.println(signUpResponse);
        System.out.println("Success");
        return "redirect:/";
    }


    @GetMapping("/asd")
    public String testPage(Model model) {
        return "/asd";
    }
}
