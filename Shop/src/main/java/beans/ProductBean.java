package beans;


import models.*;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Named
@Stateless
public class ProductBean extends GenericBean<ProductEntity> {

    @Override
    protected Class<ProductEntity> getEntityClass() {
        return ProductEntity.class;
    }


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
