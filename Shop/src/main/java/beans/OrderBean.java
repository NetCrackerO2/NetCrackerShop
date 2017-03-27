package beans;


import models.ClientEntity;
import models.OrderEntity;
import models.ProductEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Stateless
public class OrderBean extends GenericBean<OrderEntity> {

    @Override
    protected Class<OrderEntity> getEntityClass() {
        return OrderEntity.class;
    }


    /*public OrderEntity get(int id) {
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
    }*/

    public void addProductInOrder(int orderId, int productId, int count, float price) {
        try {
            OrderEntity order = em.find(OrderEntity.class, orderId);
            ProductEntity product = em.find(ProductEntity.class, productId);

            order.addProduct(product, count, price);
        } catch (EntityNotFoundException ignored) {
        }
    }

    public List<OrderEntity> getByClientId(int clientId) {
        try {
            List<OrderEntity> list = em.find(ClientEntity.class, clientId).getOrdersById();
            list.size();
            return list;
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Integer getLastOrderIdByClientId(int clientId) {
        try {
            return em.createQuery("Select MAX(e.id) from OrderEntity e where e.clientByClientId.id=:token", Integer.class)
                    .setParameter("token", clientId)
                    .getSingleResult();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
