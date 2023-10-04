package byk.pinM.service.PinService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlingWeatherService {
    public static void main(String[] args) {
        CrawlingWeatherService crawlingWeatherService = new CrawlingWeatherService();

        crawlingWeatherService.Tomo_AMPM_Temperature();
        //String text = M.Tomo_AMPM_Temperature();

    }

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

}
