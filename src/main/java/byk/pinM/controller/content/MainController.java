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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MainController {
    private static final Logger logger = LogManager.getLogger("-------------------- MainController Logging --------------------");
    @Autowired private MainContentService mainContentService;
    @Autowired private PinPointRepository pinPointRepository;
    @Autowired private PinMoneyJpaService pinMoneyJpaService;

    @GetMapping("/")
    public String indexPage() {
        logger.info(new MyUtil().getIpAddress() + "============ index Page Access");
        pinMoneyJpaService.addIpInformation(SecurityContextHolder.getContext().getAuthentication().getName(), "index");


        return "index";
    }

    @GetMapping("/content")
    public String mainPage(Model model) {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<PinPoint> pinPoint = pinPointRepository.findById(user_id);
        try {
            model.addAttribute("point", pinPoint.get().getPin_point());
            model.addAttribute("task", mainContentService.getTimePinRecord());
            model.addAttribute("spendPoint", mainContentService.entireSpendMoney(user_id));
        } catch (Exception e) {
            model.addAttribute("point", 0);
            model.addAttribute("task", false);
            model.addAttribute("spendPoint", 0);
        }

        model.addAttribute("nickname", mainContentService.getUserNickname());

        return "content/main";
    }

    @GetMapping("/logout")
    public String logoutProcess(RedirectAttributes redirectAttributes) {
        SecurityContextHolder.clearContext(); //로그인 정보 clear
        redirectAttributes.addFlashAttribute("afterMessage", "로그아웃 되었습니다.");
        return "redirect:/";
    }
}
