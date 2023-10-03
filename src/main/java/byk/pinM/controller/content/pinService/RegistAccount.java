package byk.pinM.controller.content.pinService;

import byk.pinM.entity.pinservice.PinAccount;
import byk.pinM.repository.PinAccountRepository;
import byk.pinM.service.PinService.PinMoneyJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistAccount {
    @Autowired private PinAccountRepository pinAccountRepository;
    @Autowired private PinMoneyJpaService pinMoneyJpaService;

    @GetMapping("/content/registAccount")
    public String RegistAccountView(Model model) {

        if(!pinAccountRepository.existsById(SecurityContextHolder.getContext().getAuthentication().getName())) {
            model.addAttribute("pinaccount", new PinAccount());
        }

        return "/content/registAccount";
    }

    @PostMapping("/content/registAccount-process")
    public String RegistAccountProcess(PinAccount pinAccount) {
        pinMoneyJpaService.PinAccountRegist(pinAccount);

        return "redirect:/content";
    }
}
