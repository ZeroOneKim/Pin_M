package byk.pinM.service.JPA;

import byk.pinM.entity.Account.User;
import byk.pinM.entity.Account.UserLog;
import byk.pinM.entity.pinservice.PinAccount;
import byk.pinM.entity.pinservice.PinPoint;
import byk.pinM.entity.pinservice.PinPointSpend;
import byk.pinM.repository.*;
import byk.pinM.service.PinService.MyUtil;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PIN_SERVICE 관련 메서드(JPA Method) 모음
 * @author yikim
 * @version 1.0
 * @date : 2023-10-09 최종 수정.
 */
@Service
public class PinMoneyJpaService {
    @Autowired private PinAccountRepository pinAccountRepository;
    @Autowired private AccountRepository accountRepository;
    @Autowired private UserLogRepository userLogRepository;
    @Autowired private PinPointRepository pinPointRepository;
    @Autowired private PinPointSpendRepository pinPointSpendRepository;

    /**
     * 계좌 등록정보를 받은 후 계좌 생성, 계정 정보의 권한 변경, PinPoint 관련 DB정보 생성
     * @param pinAccount : 계정정보
     */
    public void pinAccountRegist(PinAccount pinAccount, String user_id) {
        pinAccount.setUser_id(user_id);
        pinAccountRepository.save(pinAccount);

        Optional<User> oUser = accountRepository.findById(user_id);
        oUser.get().setRole_id(2);
        accountRepository.save(oUser.get());

        PinPoint pinPoint = PinPoint.builder()
                .user_id(user_id)
                .pin_point(0)
                .use_point(0).build();
        pinPointRepository.save(pinPoint);
    }

    /**
     * 접근자 IP 로그 DB 저장 -- 로그인이 안된 유저는 anonymousUser
     * @param user_id : 유저아이디
     * @param pageNm  : 접근 페이지명
     */
    public void addIpInformation(String user_id, String pageNm) throws IdentifierGenerationException {
        UserLog userLog = new UserLog();

        userLog.setLog_id(1L);
        userLog.setUser_id(user_id);
        userLog.setIp_addr(new MyUtil().getIpAddress());
        userLog.setAccess_dt(new MyUtil().getTimeNow());
        userLog.setPage_nm(pageNm);

        userLogRepository.save(userLog);
    }

    /**
     * Point 사용시, 로그처리.
     * @param user_id  : 유저ID
     * @param usePoint : 사용한 Point
     */
    public void spendPointLog(String user_id, int usePoint) {
        PinPointSpend spend = new PinPointSpend();

        spend.setSpend_id(1L);
        spend.setUser_id(user_id);
        spend.setSpend_point(usePoint);
        spend.setSpend_dt(new MyUtil().getTimeNow());

        pinPointSpendRepository.save(spend);

    }
}
