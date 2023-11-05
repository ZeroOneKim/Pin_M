package byk.pinM.controller.content;

import byk.pinM.controller.Account.SignInController;
import byk.pinM.entity.pinservice.PinPoint;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.JPA.PinMoneyJpaService;
import byk.pinM.service.MainContentService;
import byk.pinM.service.PinService.MyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class MainController {
    private static final Logger logger = LogManager.getLogger("-------------------- MainController Logging --------------------");
    @Autowired private MainContentService mainContentService;
    @Autowired private PinPointRepository pinPointRepository;
    @Autowired private PinMoneyJpaService pinMoneyJpaService;

    @GetMapping("/")
    public String indexPage() {
        logger.info("index Page Access");
        pinMoneyJpaService.addIpInformation(SecurityContextHolder.getContext().getAuthentication().getName(), "index");


        return "index";
    }

    @GetMapping("/content")
    public String mainPage(Model model) {
        Optional<PinPoint> pinPoint = pinPointRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("nickname", mainContentService.getUserNickname());
        model.addAttribute("point", pinPoint.get().getPin_point());

        return "content/main";
    }
}
