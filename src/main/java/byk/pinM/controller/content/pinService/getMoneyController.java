package byk.pinM.controller.content.pinService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class getMoneyController {
    @GetMapping("/content/getMoney")
    public String getMoneyPage() {
        return "/content/getMoney";
    }
}
