package byk.pinM.service.Account;

import byk.pinM.entity.Account.get.PasswordUpdate;
import byk.pinM.entity.Account.get.Profile;
import byk.pinM.entity.Account.get.SignUpResponse;
import byk.pinM.entity.Account.User;
import byk.pinM.repository.AccountRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * JPA -> 사용자 회원정보 및 계정 관련 JPA(쿼리) 실행 메서드 모음.
 * @author yikim
 * @version 1.0
 * @Date : 2023-09-20 최종 수정.
 */
@Service
public class AccountJpaService {
    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;

    public AccountJpaService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입 실행 메서드.
     * @param signUpResponse : 저장 전 회원 정보 입력할 파라미터 정보
     */
    public void SignUpExecute(SignUpResponse signUpResponse) {
        User user = User.builder()
                .user_id(signUpResponse.getUser_id())
                .nickname(signUpResponse.getNickname())
                .password(passwordEncoder.encode(signUpResponse.getPassword()))
                .email(signUpResponse.getEmail())
                .phone(signUpResponse.getPhone())
                .address(signUpResponse.getAddress())
                .address2(signUpResponse.getAddress2())
                .login_err_cnt(0)
                .use_lock(false)
                .text_agree(true)
                .role_id(1)
                .build();
        accountRepository.save(user);
    }

    /**
     * 회원정보 수정 메서드
     * @param profile : 수정 전 회원 정보 입력할 파라미터 정보
     */
    public void updateProfile(Profile profile) {
        Optional<User> user = accountRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        user.get().setNickname(profile.getNickname());
        user.get().setPhone(profile.getPhone());
        user.get().setAddress(profile.getAddress());
        user.get().setAddress2(profile.getAddress2());

        accountRepository.save(user.get());
    }
    /**
     * 패스워드 변경 메서드
     * @param
     */
    public void updatePassword(String newPassword) {
        Optional<User> user = accountRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        user.get().setPassword(passwordEncoder.encode(newPassword));

        accountRepository.save(user.get());
    }

}
