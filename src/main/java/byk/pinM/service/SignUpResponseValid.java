package byk.pinM.service;

import byk.pinM.entity.Account.get.SignUpResponse;
import byk.pinM.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 회원가입중, validate 적합성 체크 후 메시지 return
 * @author : yikim
 * @version 1.0
 * @Date : 2023-09-28 최종 수정.
 */
@Component
public class SignUpResponseValid implements Validator {
    @Autowired private AccountRepository accountRepository;


    @Override
    public boolean supports(Class<?> cls) {
        return cls.isAssignableFrom(SignUpResponse.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
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
