package byk.pinM.service.PinService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * 날씨 정보를 크롤링하여 Return하는 서비스 클래스
 * @authror : yikim
 * @Date : 2023-10-25 최초작성
 *
 */
@Service
public class CrawlingWeatherService {
    private static final Logger logger = LogManager.getLogger("클래스 : " + CrawlingWeatherService.class);

    public List<String> tomo_AMPM_Temperature() {
        String URL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%A0%84%EA%B5%AD%EB%82%A0%EC%94%A8";
        List<String> answer = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements temperatureElements = doc.select("a.lcl_a.local_01._marker .lcl_value._temperature");

            Element tomorrowTempA = temperatureElements.get(3);
            Element tomorrowTempB = temperatureElements.get(4);

            answer.add("내일 오전 :"+tomorrowTempA.text());
            answer.add("내일 오후 :"+tomorrowTempB.text());
        } catch(Exception e) {
            logger.error(e);
        }
        return answer;
    }

    public List<Map<String, String>> tomorrow_weather() {
        String URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%84%9C%EC%9A%B8%20%EB%82%A0%EC%94%A8";
        List<Map<String, String>> answer = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements tempElement = doc.select(".scroll_box._horizontal_scroll .graph_inner._hourly_weather .degree_point .num");
            Elements rainElement = doc.select(".scroll_box._horizontal_scroll._hourly_rain .climate_box .icon_wrap .value");
            Elements timeElement = doc.select(".scroll_box._horizontal_scroll .graph_inner._hourly_weather .time");

            boolean timeCnt = false;
            int forCnt = 26;

            for(int i = 0; i < forCnt; i++) {
                if(timeCnt) {
                    Element timeData = timeElement.get(i);
                    Element data1 = tempElement.get(i);
                    Element data2 = rainElement.get(i);
                    Map<String, String> map = new LinkedHashMap<>();

                    map.put("시간", timeData.text());
                    map.put("기온", data1.text());
                    map.put("강수확률", data2.text());

                    answer.add(map);
                }

                if(!timeCnt) {
                    Element test = timeElement.get(i);
                    if(test.text().equals("내일")) {
                        timeCnt=true;
                        forCnt = i+24;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return answer;
    }
}
