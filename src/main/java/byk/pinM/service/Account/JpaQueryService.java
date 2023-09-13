package byk.pinM.service.Account;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
public class JpaQueryService {
    private EntityManager em;

    //TODO JPQL
    public String SimpleSelectTable(String query) {
        TypedQuery<String> queryExe = em.createQuery(query, String.class);

        return "";
    }
}
