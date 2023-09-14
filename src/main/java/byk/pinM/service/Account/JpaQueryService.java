package byk.pinM.service.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class JpaQueryService {
    @Autowired private EntityManager entityManager;

    //TODO JPQL
    public String SimpleSelectTable(String query) {
        String queryExe = entityManager.createQuery(query, String.class).getSingleResult();


        return queryExe;
    }


}
