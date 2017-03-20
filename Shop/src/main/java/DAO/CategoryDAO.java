package DAO;


import models.CategoryEntity;
import models.OrderEntity;
import models.ProductEntity;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public class CategoryDAO extends AbstractDAO {
    public List<CategoryEntity> getAll() {
        try {
            return em.createQuery("SELECT e from CategoryEntity e", CategoryEntity.class)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public List<ProductEntity> getProducts(int id) {
        try {
            return em.createQuery("SELECT e from ProductEntity e where e.categoryByCategoryId.id=:token", ProductEntity.class)
                    .setParameter("token", id)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
