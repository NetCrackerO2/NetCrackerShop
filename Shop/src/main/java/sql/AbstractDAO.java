package sql;

import models.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;


public class AbstractDAO {
    @PersistenceContext
    protected EntityManager em;


    public ProductEntity get(int id) {
        try {
            return em.createQuery("SELECT e from ProductEntity e where id=:token", ProductEntity.class)
                    .setParameter("token", id)
                    .getSingleResult();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
