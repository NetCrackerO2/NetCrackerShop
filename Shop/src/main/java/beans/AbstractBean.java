package beans;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class AbstractBean {
    @PersistenceContext
    protected EntityManager em;

    <E> E persist(E entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}