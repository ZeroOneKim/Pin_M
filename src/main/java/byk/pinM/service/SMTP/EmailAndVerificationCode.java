package byk.pinM.service.SMTP;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class EmailAndVerificationCode {
    private HashMap<String, String> emailMapping = new HashMap<>();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void setEmailMapping(String email, String code) {
        this.emailMapping.put(email, code);
        removeEmailMappingCode(email);
    }

    public boolean isValidCheckCode(String email, String password) {
        System.out.println("상태 : " + emailMapping);
        if(emailMapping.get(email) != null && emailMapping.get(email).equals(password)) return true;
        return false;
    }

    private void removeEmailMappingCode(String willRemoveEmail) {
        ScheduledFuture<?> already = scheduler.schedule(() -> removeProcess(willRemoveEmail)
                                                              , 5
                                                              , TimeUnit.MINUTES);
    }

    private void removeProcess(String email) {
        emailMapping.remove(email);
        //TODO 동시 스레드, 1회차 프로세스 취소
    }


}
