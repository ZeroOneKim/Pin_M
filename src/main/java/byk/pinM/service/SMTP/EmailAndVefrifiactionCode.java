package byk.pinM.service.SMTP;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EmailAndVefrifiactionCode {
    private HashMap<String, String> emailMapping = new HashMap<>();

    public void setEmailMapping(String email, String code) {
        this.emailMapping.put(email, code);
    }

    public boolean isvalidCheckCode(String email, String password) {
        if(emailMapping.get(email) != null && emailMapping.get(email).equals(password)) return true;
        return false;
    }


}
