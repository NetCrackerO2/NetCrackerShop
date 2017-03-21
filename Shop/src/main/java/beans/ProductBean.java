package beans;


import models.*;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class ProductBean extends GenericBean<ProductEntity> {

    @Override
    protected Class<ProductEntity> getEntityClass() {
        return ProductEntity.class;
    }

    /*public List<OrderProductEntity> getOrders(int id) {
        try {
            // TODO: какое-то  с типизацией (с ней падает)
            return em.createQuery("SELECT e.orderProductsById from ProductEntity e join e.orderProductsById where e.id=:token")
                    .setParameter("token", id)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }*/

    // Получение списка товаров определённой категории
    public List<ProductEntity> getByCategory(int categoryId) {
        try {
            List<ProductEntity> list = em.createQuery("SELECT e from CategoryEntity e where e.id=:token", CategoryEntity.class)
                    .setParameter("token", categoryId)
                    .getSingleResult()
                    .getProductsById();
            list.size();
            return list;
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    // Получение содержимого заказа
    public List<ProductInOrder> getInOrder(int orderId) {
        OrderEntity order;
        try {
            order = em.createQuery("SELECT e from OrderEntity e where e.id=:token", OrderEntity.class)
                    .setParameter("token", orderId)
                    .getSingleResult();
        } catch (EntityNotFoundException e) {
            return null;
        }
        if (order == null) {
            return null;
        }

        List<ProductEntity> products = order.getProducts();
        List<OrderProductEntity> orderProductsById = order.getOrderProductsById();

        List<ProductInOrder> productsInOrder = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productsInOrder.add(new ProductInOrder());
            productsInOrder.get(i).setId(products.get(i).getId());
            productsInOrder.get(i).setName(products.get(i).getName());
            productsInOrder.get(i).setPrice(products.get(i).getPrice());
            productsInOrder.get(i).setDescription(products.get(i).getDescription());
            productsInOrder.get(i).setShoppingCount(orderProductsById.get(i).getCount());
            productsInOrder.get(i).setShoppingPrice(orderProductsById.get(i).getPrice());
        }

        return productsInOrder;
    }
}
