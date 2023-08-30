package byk.pinM.service.SMTP;

import byk.pinM.config.mySecurityInfo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderSMTP {
    //TODO SMTP-GMAIL Service / 한 IP에서 하루 최대 접근허용 제한
    private final mySecurityInfo info;

    private final JavaMailSender javaMailSender;
    public MailSenderSMTP(mySecurityInfo info, JavaMailSender javaMailSender) {
        this.info = info;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailforSignUp(String toUser, String num) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toUser);
        msg.setSubject(info.getSmtpTitle());
        msg.setText("안녕하세요 Pin_M 입니다.\n" +
                    "회원가입을 희망하시면 <인증번호 : " + num + "> 를 입력해주시기 바랍니다.");

        javaMailSender.send(msg);
    }

}
