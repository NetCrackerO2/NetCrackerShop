package sql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class AbstractDAO {
    @PersistenceContext
    protected EntityManager em;

    <E> E persist(E entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
