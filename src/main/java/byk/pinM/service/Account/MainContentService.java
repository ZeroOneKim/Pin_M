package byk.pinM.service.Account;

import byk.pinM.service.JPA.JpaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * MainContent Page에서 사용할 DB정보 및 데이터를 가져옴.
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


}
