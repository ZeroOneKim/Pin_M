package byk.pinM.controller.content.pinService;

import byk.pinM.entity.pinservice.PinAccount;
import byk.pinM.entity.pinservice.PinPoint;
import byk.pinM.repository.AccountNumRepository;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.MainContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class getMoneyController {
    @Autowired private MainContentService mainContentService;
    @Autowired private AccountNumRepository accountNumRepository;
    @Autowired private PinPointRepository pinPointRepository;

    @GetMapping("/content/getMoney")
    public String getMoneyPage(Model model) {
        Optional<PinAccount> pinAccount = accountNumRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<PinPoint> pinPoint = pinPointRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("nickname", mainContentService.getUserNickname());
        model.addAttribute("bankNm", pinAccount.get().getBank_nm());
        model.addAttribute("accountNum", pinAccount.get().getAccountnum());
        model.addAttribute("point", pinPoint.get().getPin_point());

        return "/content/getMoney";
    }

    @PostMapping("/getmoney-process")
    public String getMoneyProcess(@RequestParam("pointCh") String pt, RedirectAttributes redirectAttributes) {
        int point = 0;
        try{
            point = Integer.parseInt(pt);
            if(point <= 0) {
                redirectAttributes.addFlashAttribute("message", "값이 작습니다.");
                return "redirect:/content/getMoney";
            }
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "숫자로만 입력하세요.");
            return "redirect:/content/getMoney";
        }

        Optional<PinPoint> pinPoint = pinPointRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        if(pinPoint.get().getPin_point() < point) {
            redirectAttributes.addFlashAttribute("message", "값이 너무 큽니다.");
            return "redirect:/content/getMoney";
        } else if(pinPoint.get().getPin_point() >= point) {
            pinPoint.get().setPin_point(pinPoint.get().getPin_point() - point);
            pinPointRepository.save(pinPoint.get());

            //지급 source 1.Email 2.SNS
        }
        return "redirect:/content";
    }
}
