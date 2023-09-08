package byk.pinM.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class MainController {
    public String mainPage() {
        return "/main";
    }
}
