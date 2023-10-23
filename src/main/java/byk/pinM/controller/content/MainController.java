package byk.pinM.controller.content;

import byk.pinM.entity.pinservice.PinPoint;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.Account.MainContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired private MainContentService mainContentService;
    @Autowired private PinPointRepository pinPointRepository;

    @GetMapping("/content")
    public String mainPage(Model model) {
        Optional<PinPoint> pinPoint = pinPointRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("nickname", mainContentService.getUserNickname());
        model.addAttribute("point", pinPoint.get().getPin_point());

        return "content/main";
    }
}
