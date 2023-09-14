package byk.pinM.service.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


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
