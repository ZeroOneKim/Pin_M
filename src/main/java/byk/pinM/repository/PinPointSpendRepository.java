package byk.pinM.repository;

import byk.pinM.entity.pinservice.PinPointSpend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinPointSpendRepository extends JpaRepository<PinPointSpend, Long> {
}
