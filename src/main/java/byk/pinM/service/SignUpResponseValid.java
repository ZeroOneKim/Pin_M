package byk.pinM.service;

import byk.pinM.entity.Account.get.SignUpResponse;
import byk.pinM.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignUpResponseValid implements Validator {
    @Autowired private AccountRepository accountRepository;


    @Override
    public boolean supports(Class<?> cls) {
        return cls.isAssignableFrom(SignUpResponse.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO password, email, email_chk, phone, address, address2    // errors 사용하는법.
        SignUpResponse signUpResponse = (SignUpResponse)target;

        if(accountRepository.existsById(signUpResponse.getUser_id())) {
            errors.rejectValue("errCode", "[error] already Used : User_Id"
                    , "이미 사용중인 아이디 입니다.");
        }else if(signUpResponse.getUser_id().length() <= 4  || signUpResponse.getUser_id().length() > 16) {
            errors.rejectValue("errCode", "[error] invalid length --- User_Id"
                    ,  "아이디의 길이가 적합하지 않습니다.");
        }

        if(accountRepository.existsByNickname(signUpResponse.getNickname())) {
            errors.rejectValue("errCode", "[error] already Used : NickName"
                    ,  "이미 사용중인 닉네임 입니다.");
        }else if(signUpResponse.getNickname().length() <= 2 || signUpResponse.getNickname().length() > 16) {
            errors.rejectValue("errCode", "[error] invalid length --- NickName"
                    ,  "닉네임의 길이가 적합하지 않습니다.");
        }

        if(!signUpResponse.getPassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")) {
            errors.rejectValue("errCode", "[error] invalid Pattern --- Password"
                    ,  "[error] 유효하지않은 비밀번호입니다.");
        } else if(!signUpResponse.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.rejectValue("errCode", "[error] invalid Pattern --- email"
                    , "[error] 이메일을 확인해 주세요.");
        }
    }
}
