package byk.pinM.repository;

import byk.pinM.entity.pinservice.PinAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface pinAccountRepository extends JpaRepository<PinAccount, String> {


}
