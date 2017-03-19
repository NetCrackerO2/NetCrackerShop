package sql;


import models.OrderEntity;
import models.ProductEntity;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public class ProductDAO extends AbstractDAO {

    public List<ProductEntity> getAll() {
        try {
            return em.createQuery("SELECT e from ProductEntity e", ProductEntity.class)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }


    public ProductEntity create(ProductEntity pe) {
        return persist(pe);
    }

    public ProductEntity get(int id) {
        try {
            return em.createQuery("SELECT e from ProductEntity e where e.id=:token", ProductEntity.class)
                    .setParameter("token", id)
                    .getSingleResult();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public List<OrderEntity> getOrders(int id) {
        try {
            return em.createQuery("SELECT e.orders from ProductEntity e join e.orders where e.id=:token")
                    .setParameter("token", id)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
