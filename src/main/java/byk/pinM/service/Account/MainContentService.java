package byk.pinM.service.Account;

import byk.pinM.entity.pinservice.PinPoint;
import byk.pinM.entity.pinservice.PinPointRecord;
import byk.pinM.repository.PinPointRecordRepository;
import byk.pinM.repository.PinPointRepository;
import byk.pinM.service.JPA.JpaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * MainContent Page에서 사용할 DB관련 및 서비스 메서드 모음
 * 쿼리는 유저 입력을 받는 것이 아닌, Query 입력값을 통으로 가져가는 완성된 쿼리만을 취급한다.(SQL Injection 방지)
 * @author yikim
 * @version 1.1
 * @Date 2023-09-26 : 최초작성
 *       2023-10-18 : 최종작성
 */
@Service
public class MainContentService {
    @Autowired private JpaQueryService jpaQueryService;
    @Autowired private MainContentService mainContentService;
    @Autowired private PinPointRecordRepository pinPointRecordRepository;
    @Autowired private PinPointRepository pinPointRepository;

    /**
     * 현재 사용중인 사용자의 Nickname을 가져온다.
     * @return Nickname : 유저 닉네임
     */
    public String getUserNickname() {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        String query = "select nickname from User where user_id = '" + user_id + "'";

        String res = jpaQueryService.simpleSelectTable(query);
        return res;
    }

    /**
     * 보상금 관련 데이터를 가져온다.
     * @param number : Mission version
     * @return price : 보상금
     */
    public int getPriceByMission(int number) {
        String query = "select price FROM PinMission WHERE mission_id = '" + number +"'";
        int res = jpaQueryService.simpleIntSelectTable(query);

        return res;
    }

    /**
     * 미션 종류에 따른 포인트 적립 메서드.
     * @param missionNum : 미션넘버
     *        missionNum 1 : 날씨 관련 체크
     */
    public void WeatherChkSuccess(int missionNum) {
        int price = mainContentService.getPriceByMission(missionNum); //미션 버전에따른 보상금

        PinPointRecord checkingWeather = PinPointRecord.builder()
                .user_id(SecurityContextHolder.getContext().getAuthentication().getName())
                .mission_id(1).price(price)
                .build();
        pinPointRecordRepository.save(checkingWeather);

        Optional<PinPoint> objPoint = pinPointRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        int newPoint = objPoint.get().getPin_point() + price;
        objPoint.get().setPin_point(newPoint); //기존 포인트 + 보상 포인트
        pinPointRepository.save(objPoint.get());
    }
}
