package beans;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;


public abstract class GenericBean<T> {
    @PersistenceContext
    protected EntityManager em;

    protected abstract Class<T> getEntityClass();

    public T create(T entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public List<T> getAll() {
        try {
            return em.createQuery("SELECT e from " + getEntityClass().getSimpleName() + " e", getEntityClass())
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public T get(int id) {
        try {
            return em.find(getEntityClass(), id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
