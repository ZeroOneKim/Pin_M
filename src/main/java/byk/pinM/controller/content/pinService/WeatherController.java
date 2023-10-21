package byk.pinM.controller.content.pinService;

import byk.pinM.repository.PinPointRecordRepository;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.Account.MainContentService;
import byk.pinM.service.JPA.JpaQueryService;
import byk.pinM.service.PinService.CrawlingWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class WeatherController {
    @Autowired private CrawlingWeatherService crawlingWeatherService;
    @Autowired private MainContentService mainContentService;
    @Autowired private JpaQueryService jpaQueryService;
    @Autowired private PinPointRecordRepository pinPointRecordRepository;
    @Autowired private PinPointRepository pinPointRepository;

    @GetMapping("/content/weather")
    public String weatherView(Model model) {
        List<String> ampm_temperature = crawlingWeatherService.tomo_AMPM_Temperature();
        List<Map<String, String>> tomorrowWeatherInfo = crawlingWeatherService.tomorrow_weather();
        model.addAttribute("nickname", mainContentService.getUserNickname());
        model.addAttribute("AM", ampm_temperature.get(0));
        model.addAttribute("PM", ampm_temperature.get(1));
        model.addAttribute("weatherData", tomorrowWeatherInfo);

        mainContentService.getTimePinRecord();

        return "content/weather";
    }
    
    @PostMapping("/check-weather")
    public String weatherCheck(RedirectAttributes redirectAttributes) {
        Boolean todayChk = mainContentService.getTimePinRecord();

        if(!todayChk) {
            mainContentService.WeatherChkSuccess(1); //적립 처리
            redirectAttributes.addFlashAttribute("Message", "적립되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("Message", "오늘은 이미 적립하였습니다.");
        }


        return "redirect:/content/weather";
    }
}
