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
        //TODO user_id, nickname, password, email, email_chk, phone, address, address2
        SignUpResponse signUpResponse = (SignUpResponse) errors;

        if(accountRepository.existsById(signUpResponse.getUser_id())) {
            errors.rejectValue("user_id", "invalid --- user_id", new Object[]{signUpResponse.getUser_id()}, "이미 사용중인 아이디");
        }

    }
}
