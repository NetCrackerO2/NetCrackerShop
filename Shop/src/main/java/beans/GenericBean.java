package beans;


import javax.persistence.EntityNotFoundException;
import java.util.List;


public abstract class GenericBean<T> extends AbstractBean {
    protected abstract Class<T> getEntityClass();

    public List<T> getAll() {
        try {
            return em.createQuery("SELECT e from " + getEntityClass().getSimpleName() + " e", getEntityClass())
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public T create(T entity) {
        return persist(entity);
    }

    public T get(int id) {
        try {
            return em.find(getEntityClass(), id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
