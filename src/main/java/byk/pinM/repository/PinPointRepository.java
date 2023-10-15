package byk.pinM.repository;

import byk.pinM.entity.pinservice.PinPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PinPointRepository extends JpaRepository<PinPoint, String> {
    Optional<PinPoint> findById(String userId);
}
