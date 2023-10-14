package byk.pinM.service.JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * JPA/ JPQL 관련 쿼리 실행 메서드
 * @author yikim
 * @version 1.0
 */
@Service
public class JpaQueryService {
    @Autowired private EntityManager entityManager;

    /**
     * 쿼리 실행 결과를 단일 형태의 출력을 목적으로 return 한다.
     * @Method : simpleSelectTable : String 단일 값
     *           simpleIntSelectTable : int 단일 값
     * @param query : 실행될 쿼리.
     */
    public String simpleSelectTable(String query) {
        String queryExe = entityManager.createQuery(query, String.class).getSingleResult();
        return queryExe;
    }
    public int simpleIntSelectTable(String query) {
        int queryExe = entityManager.createQuery(query, Integer.class).getSingleResult();
        return queryExe;
    }



}
