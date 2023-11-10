package byk.pinM.controller.content.pinService;

import byk.pinM.repository.PinPointRecordRepository;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.MainContentService;
import byk.pinM.service.JPA.JPQLService;
import byk.pinM.service.PinService.CrawlingWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 날씨 정보 관련 클래스(Controller)
 * @Author : yikim
 * @Date : 2023-11-10 최종작성
 */
@Controller
public class WeatherController {
    @Autowired private CrawlingWeatherService crawlingWeatherService;
    @Autowired private MainContentService mainContentService;
    @Autowired private JPQLService JPQLService;
    @Autowired private PinPointRecordRepository pinPointRecordRepository;
    @Autowired private PinPointRepository pinPointRepository;

    /**
     * 날씨정보를 화면에 보여주는 메서드
     * @param model {nickname : 닉네임, AM : 오전 날씨, PM : 오후 날씨, weatherData : 전체 날씨/강수}
     * @return : URL
     */
    @GetMapping("/content/weather")
    public String weatherView(Model model) {
        List<String> ampm_temperature = crawlingWeatherService.tomo_AMPM_Temperature();
        List<Map<String, String>> tomorrowWeatherInfo = crawlingWeatherService.tomorrow_weather();
        model.addAttribute("nickname", mainContentService.getUserNickname());
        model.addAttribute("AM", ampm_temperature.get(0));
        model.addAttribute("PM", ampm_temperature.get(1));
        model.addAttribute("weatherData", tomorrowWeatherInfo);

        return "content/weather";
    }

    /**
     * 날씨 정보를 확인하고 적립하는 메서드
     * @return : RedirectURL
     */
    @PostMapping("/check-weather")
    public String weatherCheck(RedirectAttributes redirectAttributes) {
        Boolean todayChk = mainContentService.getTimePinRecord();

        if(!todayChk) {
            mainContentService.WeatherChkSuccess(1);
            redirectAttributes.addFlashAttribute("Message", "적립되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("Message", "오늘은 이미 적립하였습니다.");
        }

        return "redirect:/content/weather";
    }
}
