package byk.pinM.controller.content.profile;

import byk.pinM.entity.Account.User;
import byk.pinM.entity.Account.get.Profile;
import byk.pinM.service.Account.MainContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @Autowired private MainContentService mainContentService;

    @GetMapping("/content/profile")
    public String profileForm(Model model) {
        model.addAttribute("nickname", mainContentService.getUserNickname());
        User user = new User();
        model.addAttribute(new Profile(user));

        System.out.println(new Profile(user));

        return "content/profile";
    }

}
