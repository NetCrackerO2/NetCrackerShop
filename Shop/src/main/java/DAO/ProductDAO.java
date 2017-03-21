package DAO;


import models.OrderEntity;
import models.OrderProductEntity;
import models.ProductEntity;
import models.ProductInOrder;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
            return em.find(ProductEntity.class, id);
            /*return em.createQuery("SELECT e from ProductEntity e where e.id=:token", ProductEntity.class)
                    .setParameter("token", id)
                    .getSingleResult();*/
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public List<OrderProductEntity> getOrders(int id) {
        try {
            // TODO: какое-то  с типизацией (с ней падает)
            return em.createQuery("SELECT e.orderProductsById from ProductEntity e join e.orderProductsById where e.id=:token")
                    .setParameter("token", id)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public List<ProductEntity> getProductsByCategory(int categoryId) {
        try {
            return em.createQuery("SELECT e from ProductEntity e where e.categoryByCategoryId.id=:token", ProductEntity.class)
                    .setParameter("token", categoryId)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public List<ProductInOrder> getProductsInOrder(int orderId) {
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
