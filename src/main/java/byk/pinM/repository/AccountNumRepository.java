package byk.pinM.repository;

import byk.pinM.entity.pinservice.PinAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountNumRepository extends JpaRepository <PinAccount ,String> {
    Optional<PinAccount> findById(String id);
}
