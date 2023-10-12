package byk.pinM.controller.content.profile;

import byk.pinM.entity.Account.User;
import byk.pinM.entity.Account.get.PasswordUpdate;
import byk.pinM.entity.Account.get.Profile;
import byk.pinM.repository.AccountRepository;
import byk.pinM.service.JPA.AccountJpaService;
import byk.pinM.service.Account.MainContentService;
import byk.pinM.service.profile.PasswordUpdateValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @explain : URL -> content 하위에 있는 서비스 Page
 * @author : yikim
 * 최종수정 : 2023-09-20
 */
@Controller
public class ProfileController {
    @Autowired private MainContentService mainContentService;
    @Autowired private AccountRepository accountRepository;
    @Autowired private AccountJpaService accountJpaService;
    @Autowired private PasswordUpdateValid passwordUpdateValid;

    @GetMapping("/content/profile")
    public String profileForm(Model model) {
        model.addAttribute("nickname", mainContentService.getUserNickname());
        Optional<User> oUser = accountRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        Profile profile = new Profile(oUser.get());

        if(profile.getNickname().equals("")) profile.setNickname("홍길동");
        if(profile.getEmail().equals("")) profile.setEmail("미작성 상태.");
        if(profile.getAddress().equals("")) profile.setAddress("주소를 입력해 주세요");
        if(profile.getAddress2().equals("")) profile.setAddress2("상세 주소를 입력해 주세요");

        model.addAttribute("profile", profile);

        return "content/profile";
    }

    @PostMapping("/profile/update")
    public String profileUpdate(@Valid @ModelAttribute Profile profile, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute(profile);
            return "content/profile";
        }

        accountJpaService.updateProfile(profile);

        return "redirect:/content";
    }

    @GetMapping("/content/password")
    public String passwordPage(Model model) {
        model.addAttribute("nickname", mainContentService.getUserNickname());
        model.addAttribute(new PasswordUpdate());

        return "content/password";
    }

    @PostMapping("/content/passwordUpdate")
    public String passwordUpdate(@Valid PasswordUpdate passwordUpdate, Errors errors,
                                 Model model, RedirectAttributes redirectAttributes) {
        Optional<User> oUser = accountRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        //if(errors.hasErrors()) { return "redirect:/content"; }
        passwordUpdateValid.validate(passwordUpdate, errors);
        if(errors.hasErrors()) {
            redirectAttributes.addFlashAttribute("afterMessage", "패스워드 변경 실패 - 8자 이상의 비밀번호를 입력하세요.");
            return "redirect:/content/password";
        }
        //TODO 이전패스워드
        accountJpaService.updatePassword(passwordUpdate.getNewPassword()); // 비밀번호 변경
        SecurityContextHolder.clearContext(); //로그인 정보 clear
        redirectAttributes.addFlashAttribute("afterMessage", "패스워드 변경 완료(재로그인이 필요합니다.)");
        return "redirect:/";
    }






}
