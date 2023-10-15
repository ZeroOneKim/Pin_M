package byk.pinM.controller.content.pinService;

import byk.pinM.entity.pinservice.PinPoint;
import byk.pinM.entity.pinservice.PinPointRecord;
import byk.pinM.repository.PinPointRecordRepository;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.Account.MainContentService;
import byk.pinM.service.JPA.JpaQueryService;
import byk.pinM.service.PinService.CrawlingWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        return "content/weather";
    }

    //TODO 도중에 여러번 실행되면?
    @PostMapping("/check-weather") //미완
    public String weatherCheck() {
        try {
            int price = mainContentService.getPriceByMission(1);

            PinPointRecord checkingWeather = PinPointRecord.builder()
                    .user_id(SecurityContextHolder.getContext().getAuthentication().getName())
                    .mission_id(1).price(price)
                    .build();
            pinPointRecordRepository.save(checkingWeather);

            Optional<PinPoint> objPoint = pinPointRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());
            //TODO 정리 + HTML 파일 수정.
            int newPoint = objPoint.get().getPin_point() + price;
            objPoint.get().setPin_point(newPoint);
            pinPointRepository.save(objPoint.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/content/weather";
    }
}
