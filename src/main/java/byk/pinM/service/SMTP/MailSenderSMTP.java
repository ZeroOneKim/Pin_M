package byk.pinM.service.SMTP;

import byk.pinM.config.mySecurityInfo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderSMTP {
    //TODO SMTP-GMAIL Service / 한 IP에서 하루 최대 접근허용 제한
    mySecurityInfo info = new mySecurityInfo();

    private final JavaMailSender javaMailSender;
    public MailSenderSMTP(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailforSignUp(String toUser, String num) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toUser);
        msg.setSubject(info.getSmtpTitle());
        msg.setText(num);

        javaMailSender.send(msg);
    }

}
