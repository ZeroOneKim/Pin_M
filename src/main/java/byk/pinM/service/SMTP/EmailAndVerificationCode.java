package byk.pinM.service.SMTP;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class EmailAndVerificationCode {
    private HashMap<String, String> emailMapping = new HashMap<>();
    private Timer timer = new Timer();

    public void setEmailMapping(String email, String code) {
        this.emailMapping.put(email, code);

        timer.schedule(new RemoveEmailMappingCode(email), 30000);
    }

    public boolean isValidCheckCode(String email, String password) {
        System.out.println("상태 : " + emailMapping);
        if(emailMapping.get(email) != null && emailMapping.get(email).equals(password)) return true;
        return false;
    }

    private class RemoveEmailMappingCode extends TimerTask {
        private String willRemoveEmail;

        private RemoveEmailMappingCode(String willRemoveEmail) {
            this.willRemoveEmail = willRemoveEmail;
        }

        public void run() {
            emailMapping.remove(willRemoveEmail);
        }
    }

}
