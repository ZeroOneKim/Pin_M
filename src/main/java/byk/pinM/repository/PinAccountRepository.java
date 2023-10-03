package byk.pinM.repository;

import byk.pinM.entity.pinservice.PinAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PinAccountRepository extends JpaRepository<PinAccount, String> {
    boolean existsById(String id);

}
