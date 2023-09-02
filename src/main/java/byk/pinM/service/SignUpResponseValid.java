package byk.pinM.service;

import byk.pinM.entity.Account.SignUpResponse;
import byk.pinM.repository.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignUpResponseValid implements Validator {
    private AccountRepository accountRepository;

    public void SignUpResponseValid(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean supports(Class<?> cls) {
        return cls.isAssignableFrom(SignUpResponse.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO password, email, email_chk, phone, address, address2
        SignUpResponse signUpResponse = (SignUpResponse) errors;

        if(accountRepository.existsById(signUpResponse.getUser_id())) {
            errors.rejectValue("user_id", "[error] already Used : User_Id"
                    , new Object[]{signUpResponse.getUser_id()}, "이미 사용중인 아이디 입니다.");
        } else if (signUpResponse.getUser_id().length() <= 5) {
            errors.rejectValue("user_id", "[error] invalid length --- User_Id"
                    , new Object[]{signUpResponse.getUser_id()}, "아이디의 길이가 적합하지 않습니다.");
        }

        if(accountRepository.existsByNickname(signUpResponse.getNickname())) {
            errors.rejectValue("nickname", "[error] already Used : NickName"
                    , new Object[]{signUpResponse.getNickname()}, "이미 사용중인 닉네임 입니다.");
        } else if(signUpResponse.getNickname().length() <= 5) {
            errors.rejectValue("nickname", "[error] invalid length --- NickName"
                    , new Object[]{signUpResponse.getNickname()}, "닉네임의 길이가 적합하지 않습니다.");
        }



    }
}
