package byk.pinM.service;

import byk.pinM.entity.Account.SignUpResponse;
import byk.pinM.entity.Account.User;
import byk.pinM.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JpaService {
    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;


    public JpaService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //SignUp
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
                .build();
        accountRepository.save(user);

    }


}
