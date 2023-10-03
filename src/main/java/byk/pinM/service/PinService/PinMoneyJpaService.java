package byk.pinM.service.PinService;

import byk.pinM.entity.Account.User;
import byk.pinM.entity.pinservice.PinAccount;
import byk.pinM.repository.AccountRepository;
import byk.pinM.repository.PinAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PinMoneyJpaService {
    @Autowired private PinAccountRepository pinAccountRepository;
    @Autowired private AccountRepository accountRepository;

    public void PinAccountRegist(PinAccount pinAccount) {
        pinAccount.setUser_id(SecurityContextHolder.getContext().getAuthentication().getName());
        pinAccountRepository.save(pinAccount);

        Optional<User> oUser = accountRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        oUser.get().setRole_id(2);
        accountRepository.save(oUser.get());
    }
}
