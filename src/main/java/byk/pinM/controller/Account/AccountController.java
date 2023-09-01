package byk.pinM.controller.Account;

import byk.pinM.entity.Account.SignUpResponse;
import byk.pinM.service.SMTP.EmailAndVefrifiactionCode;
import byk.pinM.service.SMTP.SignUpEmailChk;
import byk.pinM.service.SignUpResponseValid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    private static final Logger logger = LogManager.getLogger("클래스 : " + AccountController.class + "---------------------");
    private SignUpResponseValid signUpResponseValid;
    private SignUpEmailChk signUpEmailChk;
    private EmailAndVefrifiactionCode emailAndVefrifiactionCode;

    public AccountController(SignUpEmailChk signUpEmailChk) {
        this.signUpEmailChk = signUpEmailChk;
    }

    @Autowired
    public void SignUpResponseValidMethod (SignUpResponseValid signUpResponseValid) {
        this.signUpResponseValid = signUpResponseValid;
    }
    @Autowired
    public void setEmailAndVefrifiactionCode(EmailAndVefrifiactionCode emailAndVefrifiactionCode) {
        this.emailAndVefrifiactionCode = emailAndVefrifiactionCode;
    }

    @GetMapping("/signIn")
    public String signIn(Model model) {
        EmailAndVefrifiactionCode emailAndVefrifiactionCode = new EmailAndVefrifiactionCode();

        return "account/signIn";
    }




    //SignUp Part
    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("SignUpResponse", new SignUpResponse());

        return "account/signUp";
    }

    @PostMapping("/signUpEmailChk")
    @ResponseBody
    public void emailChk_Process(@RequestParam("data") String email) {
        System.out.println(email + "에게 메시지 전송 준비");
        signUpEmailChk.sendEmailAndGenerateRandomNumber(email);
    }
    @PostMapping("/signUpEmailChkWithNum")
    @ResponseBody
    public void emailNumberChk_Process(@RequestParam("data") String email, @RequestParam("data2") String validNum) {
        System.out.println(email + "   " + validNum);
        boolean emailChkToken = emailAndVefrifiactionCode.isvalidCheckCode(email, validNum);
        System.out.println("이메일 인증 상태 : " + emailChkToken);
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

}
