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


    @GetMapping("/signIn")
    public String signIn(Model model) {
        return "account/signIn";
    }



    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("SignUpResponse", new SignUpResponse());
        return "account/signUp";
    }
    @PostMapping("/signUp_Process")
    public String signUpResponse(@ModelAttribute SignUpResponse signUpResponse, Errors errors) { //Error Code 작성 필요.
        /*SignUpResponseValidMethod(signUpResponseValid);
        signUpResponseValid.validate(signUpResponse, errors);*/
        if(errors.hasErrors()) {
            System.out.println("failed");
            System.out.println("ERROR STATUS(값 체크) : " + errors.hasErrors());
            return "account/signUp";  //에러처리 예정
        }

        System.out.println("ERROR STATUS(값 체크) : " + errors.hasErrors());

        System.out.println(signUpResponse.getUser_id());
        System.out.println("Success");
        return "redirect:/";
    }

    //테스트용
    @GetMapping("/asd")
    public String testPage(Model model) {
        return "/asd";
    }
}
