package byk.pinM.controller.content.profile;

import byk.pinM.entity.Account.User;
import byk.pinM.entity.Account.get.Profile;
import byk.pinM.repository.AccountRepository;
import byk.pinM.service.Account.MainContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired private MainContentService mainContentService;
    @Autowired private AccountRepository accountRepository;

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
    public String profileUpdate(@ModelAttribute Profile profile) {
        //Validated 사용법 파악
        return null;
    }
}
