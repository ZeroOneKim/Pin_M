package byk.pinM.controller.Account;

import byk.pinM.entity.Account.SignUpResponse;
import byk.pinM.service.JpaService;
import byk.pinM.service.SMTP.EmailAndVerificationCode;
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
    private boolean isEmailCheck = false;

    private static final Logger logger = LogManager.getLogger("클래스 : " + AccountController.class + "---------------------");
    private SignUpResponseValid signUpResponseValid;
    private SignUpEmailChk signUpEmailChk;
    private EmailAndVerificationCode emailAndVerificationCode;
    private JpaService jpaService;

    @Autowired
    public AccountController(SignUpEmailChk signUpEmailChk, JpaService jpaService) {
        this.signUpEmailChk = signUpEmailChk;
        this.jpaService = jpaService;
    }

    @Autowired
    public void SignUpResponseValidMethod (SignUpResponseValid signUpResponseValid) {
        this.signUpResponseValid = signUpResponseValid;
    }
    @Autowired
    public void setEmailAndVefrifiactionCode(EmailAndVerificationCode emailAndVerificationCode) {
        this.emailAndVerificationCode = emailAndVerificationCode;
    }

    @GetMapping("/signIn")
    public String signIn(Model model) {
        return "account/signIn";
    }




    //====================================================== SignUp Part ===========================================================
    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("SignUpResponse", new SignUpResponse());

        return "account/signUp";
    }
    //이메일 전송
    @PostMapping("/signUpEmailChk")
    @ResponseBody
    public void emailChk_Process(@RequestParam("data") String email) {
        System.out.println(email + "에게 메시지 전송 준비");
        signUpEmailChk.sendEmailAndGenerateRandomNumber(email); //Send Email
    }
    //이메일 확인
    @PostMapping("/signUpEmailChkWithNum")
    @ResponseBody
    public void emailNumberChk_Process(@RequestParam("data") String email, @RequestParam("data2") String validNum) {
        System.out.println(email + "   " + validNum);
        isEmailCheck = emailAndVerificationCode.isValidCheckCode(email, validNum);
        System.out.println("이메일 인증 상태 : " + isEmailCheck); //TODO True or False 회원가입 이전에 체킹
    }

    //회원가입 버튼
    @PostMapping("/signUp_Process")
    public String signUpResponse(@ModelAttribute SignUpResponse signUpResponse, Errors errors) { //TODO Error Code 작성 필요.
        if(errors.hasErrors()) {
            return "account/signUp";
        }
        SignUpResponseValidMethod(signUpResponseValid);
        signUpResponseValid.validate(signUpResponse, errors);
        if(errors.hasErrors()) {
            System.out.println("failed");
            System.out.println("ERROR STATUS(값 체크) : " + errors.hasErrors());
            return "account/signUp";
        }

        if(isEmailCheck) { //후에 ! 붙이기
            return "account/signUp";
        }

        jpaService.SignUpExecute(signUpResponse);

        return "redirect:/";
    }

}
