package byk.pinM.service.profile;

import byk.pinM.entity.Account.get.PasswordUpdate;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PasswordUpdateValid implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordUpdate.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordUpdate passwordUpdate = (PasswordUpdate) target;
        if(!passwordUpdate.getNewPassword().equals(passwordUpdate.getPassword())) {
            errors.rejectValue("newPassword", "wrong.value", "패스워드가 불일치 합니다.");
        }
    }
}
