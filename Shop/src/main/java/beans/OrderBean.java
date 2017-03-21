package beans;


import models.OrderEntity;
import models.ProductEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Stateless
public class OrderBean extends AbstractBean {

    public OrderEntity get(int id) {
        try {
            return em.find(OrderEntity.class, id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public List<OrderEntity> getAll() {
        try {
            return em.createQuery("SELECT e from OrderEntity e", OrderEntity.class)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void addProductInOrder(int orderId, int productId, int count, float price) {
        try {
            OrderEntity order = em.find(OrderEntity.class, orderId);
            ProductEntity product = em.find(ProductEntity.class, productId);

            order.addProduct(product, count, price);
        } catch (EntityNotFoundException ignored) {
        }
    }
}
