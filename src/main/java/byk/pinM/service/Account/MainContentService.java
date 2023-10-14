package byk.pinM.service.Account;

import byk.pinM.service.JPA.JpaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * MainContent Page에서 사용할 DB정보 및 데이터를 가져옴.
 * 유저 입력을 받는 것이 아닌, Query 입력값을 통으로 가져가는 완성된 쿼리만을 취급한다.(SQL Injection 방지)
 * @author yikim
 * @version 1.0
 */
@Service
public class MainContentService {
    @Autowired
    private JpaQueryService jpaQueryService;

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
}
