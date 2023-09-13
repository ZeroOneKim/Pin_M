package byk.pinM.controller.content;

import byk.pinM.service.Account.MainContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private MainContentService mainContentService;

    public MainController(MainContentService mainContentService) {
        this.mainContentService = mainContentService;
    }

    @GetMapping("/content")
    public String mainPage() {
        System.out.println(mainContentService.getUserNickname());

        return "content/main";
    }
}
