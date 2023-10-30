package byk.pinM.service.JPA;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * JPQL 관련 쿼리 실행 메서드
 * @author yikim
 * @version 1.0
 */
@Service
public class JPQLService {
    private static final Logger logger = LogManager.getLogger("클래스 ERR] : " + JPQLService.class);
    @Autowired private EntityManager entityManager;

    /**
     * 쿼리 실행 결과를 단일 형태의 출력을 목적으로 return 한다.
     * @Method : simpleSelectTable : String 단일 값
     *           simpleIntSelectTable : int 단일 값
     * @param query : 실행될 쿼리.
     */
    public String simpleSelectTable(String query) throws NoResultException {
        try {
            String queryExe = entityManager.createQuery(query, String.class).getSingleResult();
            if (queryExe.isEmpty()) {
                queryExe = "";
            }
            return queryExe;
        } catch (NoResultException e) {
            return "";
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    public int simpleIntSelectTable(String query) throws NoResultException {
        int queryExe = entityManager.createQuery(query, Integer.class).getSingleResult();
        return queryExe;
    }



}
