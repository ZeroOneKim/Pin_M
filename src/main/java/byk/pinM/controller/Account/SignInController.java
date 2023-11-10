package byk.pinM.controller.Account;

import byk.pinM.service.JPA.PinMoneyJpaService;
import byk.pinM.service.PinService.CrawlingWeatherService;
import byk.pinM.service.PinService.MyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 로그인 관련 클래스(Controller)
 * @Author : yikim
 * @Date : 2023-09-25
 */
@Controller
public class SignInController {
    private static final Logger logger = LogManager.getLogger("클래스 : " + SignInController.class);
    @Autowired private PinMoneyJpaService pinMoneyJpaService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private CrawlingWeatherService crawlingWeatherService;

    /**
     * 로그인 View Page 접근
     * @option : 접근자 DB에 IP 로깅 처리. .
     * @return urlAddress
     */
    @GetMapping("/signIn")
    public String signIn() {
        logger.info(new MyUtil().getIpAddress() + " =====  Sign In 페이지 접근 중. . .");
        pinMoneyJpaService.addIpInformation(SecurityContextHolder.getContext().getAuthentication().getName(), "/signIn");
        return "account/signIn";
    }

    /**
     * ID/ password를 입력받고 권한 부여 후 redirect 메서드.
     * @param id : userId
     * @param password : PWD
     * @return
     */
    @PostMapping("/signIn-process")
    public String signInProcess(@RequestParam("user_id") String id, @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, password);
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            return "redirect:/content";
        } catch (Exception e) {
            logger.error("\n ERROR] : SignIn 실패. \nReason : " + e);
            return "account/signIn";
        }
    }

}
