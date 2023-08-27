package byk.pinM.service.SMTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SignUpEmailChk {
    private MailSenderSMTP mailSenderSMTP;
    private  String chkEmail_Id;

    @Autowired
    public void setMailSenderSMTP(MailSenderSMTP mailSenderSMTP) {
        this.mailSenderSMTP = mailSenderSMTP;
    }

    public void setChkEmail_Id(String chkEmail_Id) {
        this.chkEmail_Id = chkEmail_Id;
    }

    public void sendEmailAndGenerateRandomNumber() {
        if (chkEmail_Id != null) {
            String val = RandomNumber();
            mailSenderSMTP.sendEmailforSignUp(chkEmail_Id, val);
        }
    }

    String RandomNumber() {
        Random random = new Random();
        return "1561";
    }


}
