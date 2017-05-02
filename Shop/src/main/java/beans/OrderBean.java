package beans;


import models.ClientEntity;
import models.OrderEntity;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Named
@Stateless
public class OrderBean extends GenericBean<OrderEntity> {

    @Override
    protected Class<OrderEntity> getEntityClass() {
        return OrderEntity.class;
    }

    /*public void addProductInOrder(int orderId, int productId, int count, float price) {
        try {
            OrderEntity order = em.find(OrderEntity.class, orderId);
            ProductEntity product = em.find(ProductEntity.class, productId);

            order.addProduct(product, count, price);
        } catch (EntityNotFoundException ignored) {
        }
    }*/

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

    @Override
    public boolean canRemove(OrderEntity entity) {
        // TODO Auto-generated method stub
        return true;
    }
}
