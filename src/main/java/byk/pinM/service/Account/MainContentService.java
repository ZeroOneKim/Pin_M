package byk.pinM.service.Account;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class MainContentService {
    public String getUserNickname() {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();

        return user_id;
    }
}
