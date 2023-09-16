package byk.pinM.service.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * MainContent Page에서 사용할 DB정보 및 데이터를 가져옴.
 * @author yikim
 * @version 1.0
 */

@Service
public class MainContentService {
    @Autowired
    private JpaQueryService jpaQueryService;

    public String getUserNickname() {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        String query = "select nickname from User where user_id = '" + user_id + "'";

        String res = jpaQueryService.SimpleSelectTable(query);
        return res;
    }


}
