package byk.pinM.service.SMTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SignUpEmailChk {
    private EmailAndVerificationCode emailAndVerificationCode;

    private MailSenderSMTP mailSenderSMTP;
    @Autowired
    public void SignUpEmailChk(MailSenderSMTP mailSenderSMTP) {
        this.mailSenderSMTP = mailSenderSMTP;
    }

    @Autowired
    public void setEmailAndVefrifiactionCode(EmailAndVerificationCode emailAndVerificationCode) {
        this.emailAndVerificationCode = emailAndVerificationCode;
    }

    public void sendEmailAndGenerateRandomNumber(String chkEmail_Id) {
        if (!chkEmail_Id.equals("")) {
            String val = randomNumber();
            mailSenderSMTP.sendEmailforSignUp(chkEmail_Id, val);
            emailAndVerificationCode.setEmailMapping(chkEmail_Id, val);
        }
    }

    String randomNumber() {
        Random random = new Random();
        int intAnswer = random.nextInt(8999)+1000;
        String answer = String.valueOf(intAnswer);
        return answer;
    }


}
