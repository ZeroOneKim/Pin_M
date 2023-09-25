package byk.pinM.controller.content.pinService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistAccount {
    @GetMapping("/content/registAccount")
    public String RegistAccountView() {
        return "/content/registAccount";
    }
}
