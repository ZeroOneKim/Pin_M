package byk.pinM.service.PinService;

import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Service
public class CrawlingWeatherService {

    public String Tomo_AMPM_Temperature() {
        String URL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%A0%84%EA%B5%AD%EB%82%A0%EC%94%A8";
        String answer = "";
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements temperatureElements = doc.select("a.lcl_a.local_01._marker .lcl_value._temperature");

            Element tomorrowTempA = temperatureElements.get(3);
            Element tomorrowTempB = temperatureElements.get(4);

            answer = "내일 오전 :" + tomorrowTempA.text()
                    + "   내일 오후 :" + tomorrowTempB.text();
        } catch(Exception e) {

        }
        return answer;
    }
    public String TomoRain_per() {
        String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%84%9C%EC%9A%B8%20%EB%82%A0%EC%94%A8";
        String answer = "";
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements ele = doc.select(".scroll_box._horizontal_scroll._hourly_rain .climate_box "
                    + ".icon_wrap");

            System.out.println(ele);
        } catch (Exception e) {

        }
        return "";
    }
}
