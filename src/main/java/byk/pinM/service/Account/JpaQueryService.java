package byk.pinM.service.Account;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class JpaQueryService {
    private EntityManager em;

    public JpaQueryService(EntityManager em) {
        this.em = em;
    }

    //TODO JPQL
    public String SimpleSelectTable(String query) {
        String queryExe = em.createQuery(query, String.class).getSingleResult();


        return queryExe;
    }


}
