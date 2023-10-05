package byk.pinM.controller.Account;

import byk.pinM.entity.Account.get.SignUpResponse;
import byk.pinM.service.Account.AccountJpaService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * 회원가입 관련 컨트롤러.
 * @author yikim
 * @version 1.0
 * @Date : 2023-09-25 최종수정.
 */
@Controller
public class SignUpController {
    private static final Logger logger = LogManager.getLogger("클래스 Log] " + SignUpController.class);

    private boolean isEmailCheck = false;

    @Autowired private SignUpEmailChk signUpEmailChk;
    @Autowired private EmailAndVerificationCode emailAndVerificationCode;
    @Autowired private AccountJpaService accountJpaService;
    @Autowired private SignUpResponseValid signUpResponseValid;

    //회원 가입 뷰 페이지
    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("SignUpResponse", new SignUpResponse());
        return "account/signUp";
    }

    /**
     * 해당 이메일에 검증을 위한 난수의 비밀번호 값을 보낸다.
     * @param email : 가입하고자 하는 유저의 Email
     */
    @PostMapping("/signUpEmailChk")
    @ResponseBody
    public void emailChk_Process(@RequestParam("data") String email) {
        signUpEmailChk.sendEmailAndGenerateRandomNumber(email);
    }

    /**
     * 해당 이메일에 보낸 비밀번호값과 일치하는지 확인한다.
     * @param email
     * @param validNum
     */
    @PostMapping("/signUpEmailChkWithNum")
    @ResponseBody
    public void emailNumberChk_Process(@RequestParam("data") String email, @RequestParam("data2") String validNum) {
        isEmailCheck = emailAndVerificationCode.isValidCheckCode(email, validNum);
    }

    /**
     * 모든 정보를 입력 후 회원가입 신청을 한다.
     * @param signUpResponse : 회원가입 정보 Form
     * @param errors : error Message
     * Annotation : @Valid - 유효성 검사
     */
    @PostMapping("/signUp_Process")
    public String signUpResponse(@Valid @ModelAttribute SignUpResponse signUpResponse, Errors errors, RedirectAttributes redirectAttributes) {
        if(errors.hasErrors()) {
            return "redirect:/signUp";
        }
        signUpResponseValid.validate(signUpResponse, errors);
        if(errors.hasErrors()) {
            String strErrCode = errors.getFieldError("errCode").getDefaultMessage();
            redirectAttributes.addFlashAttribute("afterMessage", strErrCode);
            return "redirect:/signUp";
        }
        //이메일 체크 실패 시.
        if(!isEmailCheck) {
            redirectAttributes.addFlashAttribute("afterMessage", "이메일 인증이 되지 않았습니다.");
            return "redirect:/signUp";
        }

        accountJpaService.signUpExecute(signUpResponse);
        redirectAttributes.addFlashAttribute("afterMessage", "가입 완료.");
        return "redirect:/";
    }

}
