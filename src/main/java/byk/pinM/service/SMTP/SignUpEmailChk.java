package byk.pinM.service.SMTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class SignUpEmailChk {
    Logger logger = LogManager.getLogger("Email Id Chk Logic ======================================");
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
            logger.info("객체 테스트 ============================================================\n" +emailAndVefrifiactionCode);
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
