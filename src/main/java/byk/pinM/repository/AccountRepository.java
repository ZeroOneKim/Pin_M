package byk.pinM.repository;

import byk.pinM.entity.Account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<User, String> {
    boolean existsById(String user_id);

    boolean existsByNickname(String nickname);

    Optional<User> findById(String userId);

}
