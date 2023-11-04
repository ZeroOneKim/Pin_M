package byk.pinM.controller.content.pinService;

import byk.pinM.entity.pinservice.PinAccount;
import byk.pinM.repository.PinAccountRepository;
import byk.pinM.service.MainContentService;
import byk.pinM.service.JPA.PinMoneyJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistAccountNumController {
    @Autowired private PinAccountRepository pinAccountRepository;
    @Autowired private PinMoneyJpaService pinMoneyJpaService;
    @Autowired private MainContentService mainContentService;

    @GetMapping("/content/registAccount")
    public String registAccountView(Model model) {
        model.addAttribute("nickname", mainContentService.getUserNickname());
        if(!pinAccountRepository.existsById(SecurityContextHolder.getContext().getAuthentication().getName())) {
            model.addAttribute("pinaccount", new PinAccount());
        }
        model.addAttribute("pinaccount", new PinAccount());
        return "content/registAccount";
    }

    @PostMapping("/content/registAccount-process")
    public String registAccountProcess(PinAccount pinAccount) {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        pinMoneyJpaService.pinAccountRegist(pinAccount, user_id);

        return "redirect:/content";
    }
}
