package byk.pinM.controller.content;

import byk.pinM.service.Account.MainContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired private MainContentService mainContentService;

    @GetMapping("/content")
    public String mainPage(Model model) {
        model.addAttribute("nickname", mainContentService.getUserNickname());

        return "content/main";
    }
}
