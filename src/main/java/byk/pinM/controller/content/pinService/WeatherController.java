package byk.pinM.controller.content.pinService;

import byk.pinM.entity.pinservice.PinPointRecord;
import byk.pinM.service.Account.MainContentService;
import byk.pinM.service.PinService.CrawlingWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class WeatherController {
    @Autowired private CrawlingWeatherService crawlingWeatherService;
    @Autowired private MainContentService mainContentService;

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

    @PostMapping("/check-weather") //미완
    public String weatherCheck() {
        try {
            PinPointRecord checkingWeather = new PinPointRecord()
                    .builder().user_id(SecurityContextHolder.getContext().getAuthentication().getName())
                    .mission_id(1).price() //TODO Query Price data
                    .build();

            //TODO Pin POINT MT 추가처리. DB작업

        } catch (Exception e) {
            //TODO
        }
        return "redirect:/content/weather";
    }
}
