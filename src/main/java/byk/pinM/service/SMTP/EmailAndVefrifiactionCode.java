package byk.pinM.service.SMTP;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EmailAndVefrifiactionCode {
    private HashMap<String, String> emailMapping = new HashMap<>();

    public void setEmailMapping(String email, String code) {
        emailMapping.put(email, code);
    }

    public Boolean chkEmailMapping(String email, String password) {
        if(emailMapping.containsKey(email)) {

            return true;
        } else {
            return false;
        }
    }
}
