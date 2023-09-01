package byk.pinM.service.SMTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SignUpEmailChk {
    private EmailAndVefrifiactionCode emailAndVefrifiactionCode;

    private MailSenderSMTP mailSenderSMTP;
    @Autowired
    public void SignUpEmailChk(MailSenderSMTP mailSenderSMTP) {
        this.mailSenderSMTP = mailSenderSMTP;
    }

    @Autowired
    public void setEmailAndVefrifiactionCode(EmailAndVefrifiactionCode emailAndVefrifiactionCode) {
        this.emailAndVefrifiactionCode = emailAndVefrifiactionCode;
    }

    public void sendEmailAndGenerateRandomNumber(String chkEmail_Id) {
        if (!chkEmail_Id.equals("")) {
            String val = RandomNumber();
            mailSenderSMTP.sendEmailforSignUp(chkEmail_Id, val);
            emailAndVefrifiactionCode.setEmailMapping(chkEmail_Id, val);
        }
    }

    String RandomNumber() {
        Random random = new Random();
        int intAnswer = random.nextInt(8999)+1000;
        String answer = String.valueOf(intAnswer);
        return answer;
    }


}
