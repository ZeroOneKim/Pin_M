package byk.pinM.repository;

import byk.pinM.entity.pinservice.PinPointRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinPointRecordRepository extends JpaRepository<PinPointRecord, String> {
}
