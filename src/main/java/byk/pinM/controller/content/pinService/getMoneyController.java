package byk.pinM.controller.content.pinService;

import byk.pinM.entity.Account.User;
import byk.pinM.entity.pinservice.PinAccount;
import byk.pinM.entity.pinservice.PinPoint;
import byk.pinM.repository.AccountNumRepository;
import byk.pinM.repository.AccountRepository;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.JPA.PinMoneyJpaService;
import byk.pinM.service.MainContentService;
import byk.pinM.service.SMTP.MailSenderSMTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Point -> 현금으로 변환하는 클래스(Controller)
 * @author : 김영일
 * @Date : 2023-10-17 최초작성
 *       : 2023-11-08 새 가입자 로그인 불가현상 수정
 */
@Controller
public class getMoneyController {
    @Autowired private MainContentService mainContentService;
    @Autowired private AccountNumRepository accountNumRepository;
    @Autowired private AccountRepository accountRepository;
    @Autowired private PinPointRepository pinPointRepository;
    @Autowired private PinMoneyJpaService pinMoneyJpaService;
    @Autowired private MailSenderSMTP mailSenderSMTP;

    /**
     * 포인트 전환 Page View Method
     * @param model [닉네임/ 은행명/ 계좌번호/ point]
     * @return URL
     */
    @GetMapping("/content/getMoney")
    public String getMoneyPage(Model model) {
        pinMoneyJpaService.addIpInformation(SecurityContextHolder.getContext().getAuthentication().getName(), "/getMoney");
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<PinAccount> pinAccount = accountNumRepository.findById(user_id);
        Optional<PinPoint> pinPoint = pinPointRepository.findById(user_id);

        model.addAttribute("nickname", mainContentService.getUserNickname());
        try {
            model.addAttribute("bankNm", pinAccount.get().getBank_nm());
            model.addAttribute("accountNum", pinAccount.get().getAccountnum());
            model.addAttribute("point", pinPoint.get().getPin_point());
        } catch (Exception e) {
            model.addAttribute("bankNm", "");
            model.addAttribute("accountNum", "");
            model.addAttribute("point", "");
        }
        return "content/getMoney";
    }

    /**
     * Point -> 현금 교환 요청 메서드
     * 1. 값 검증
     * 2. 포인트 차감
     * 3. 기록 DB 저장
     * @param pt : 요청 금액
     * @param redirectAttributes : redirect content
     * @return RedirectURL
     */
    @PostMapping("/getmoney-process")
    public String getMoneyProcess(@RequestParam("pointCh") String pt, RedirectAttributes redirectAttributes) {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
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

        Optional<PinPoint> pinPoint = pinPointRepository.findById(user_id);

        if(pinPoint.get().getPin_point() < point) {
            redirectAttributes.addFlashAttribute("message", "값이 너무 큽니다.");
            return "redirect:/content/getMoney";
        } else if(pinPoint.get().getPin_point() >= point) {
            pinPoint.get().setPin_point(pinPoint.get().getPin_point() - point);
            pinPointRepository.save(pinPoint.get());

            pinMoneyJpaService.spendPointLog(user_id, point);

            mailSenderSMTP.sendEmailRequestMoney(user_id, point, accountRepository.findById(user_id).get().getPhone()
                                                               , accountNumRepository.findById(user_id).get().getAccountnum()
                                                               , accountNumRepository.findById(user_id).get().getBank_nm());
        }
        return "redirect:/content";
    }
}
