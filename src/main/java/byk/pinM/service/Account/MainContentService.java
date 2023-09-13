package byk.pinM.service.Account;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class MainContentService {
    private JpaQueryService jpaQueryService;

    public MainContentService(JpaQueryService jpaQueryService) {
        this.jpaQueryService = jpaQueryService;
    }


    public String getUserNickname() {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        String query = "select nickname from User where user_id = '" + user_id + "'";


        String res = jpaQueryService.SimpleSelectTable(query);
        System.out.println(res);
        return res;
    }
}
