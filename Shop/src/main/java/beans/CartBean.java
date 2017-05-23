package beans;


import clientInfo.AdminInterceptor;
import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;
import models.CartEntity;
import models.OrderEntity;
import models.OrderProductEntity;
import models.ProductEntity;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Named
@Stateless
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
@NeedAuthorization
public class CartBean extends GenericBean<CartEntity> {
    @Inject
    ProductBean productBean;

    @Inject
    OrderBean orderBean;

    @Inject
    ClientBean clientBean;

    @Inject
    ClientInfo clientInfo;

    @Override
    protected Class<CartEntity> getEntityClass() {
        return CartEntity.class;
    }


    // Возвращает наполнение корзины пользователя
    public List<CartEntity> getCart() {
        try {
            return em.createQuery("select e from CartEntity e where e.clientId=:token", CartEntity.class)
                     .setParameter("token", clientInfo.getId())
                     .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    // Добавляет новый товар в корзину либо изменяет количество существующего
    public boolean addProductInCart(int productId, int count) {
        CartEntity cart;
        try {
            cart = em.createQuery("select e from CartEntity e where e.clientId=:token1 and e.productId=:token2",
                                  CartEntity.class)
                     .setParameter("token1", clientInfo.getId())
                     .setParameter("token2", productId)
                     .getSingleResult();
        } catch (EntityNotFoundException | NoResultException e) {
            cart = new CartEntity();
            cart.setClientId(clientInfo.getId());
            cart.setProductId(productId);
            cart.setCount(count);
            return persist(cart) != null;
        }

        cart.setCount(count);
        return true;
    }

    // Удаляет указанный товар из корзины пользователя, если он в ней есть
    public void removeProductFromCart(int productId) {
        CartEntity cart;
        try {
            cart = em.createQuery("select e from CartEntity e where e.clientId=:token1 and e.productId=:token2",
                                  CartEntity.class)
                     .setParameter("token1", clientInfo.getId())
                     .setParameter("token2", productId)
                     .getSingleResult();
        } catch (EntityNotFoundException | NoResultException e) {
            return;
        }

        em.remove(cart);
    }

    // Оформляет заказ на основе корзины пользователя и очищает корзину
    public void createOrder(String address) {
        List<CartEntity> cart = getCart();
        if (cart.size() == 0) {
            throw new EJBException("Корзина пуста");
        }

        if (address == null || Objects.equals(address, "")) {
            address = clientBean.get(clientInfo.getId()).getDefaultAddress();
        }

        java.util.Date currentDate = new java.util.Date();
        Date date = new Date(currentDate.getTime());

        OrderEntity order = new OrderEntity();
        order.setClientByClientId(clientBean.get(clientInfo.getId()));
        order.setAddress(address);
        order.setDate(date);


        for (CartEntity entity : cart) {
            ProductEntity product = productBean.get(entity.getProductId());
            int count = entity.getCount();
            BigDecimal price = product.getPrice();

            if (product.getCount() < count) {
                throw new EJBException("Нет в наличии необходимого количества "
                                               + product.getName()
                                               + ": есть "
                                               + product.getCount()
                                               + " шт., а надо "
                                               + entity.getCount()
                                               + " шт."
                );
            } else {
                product.setCount(product.getCount() - count);
            }
            if (product.getDisabled())
                throw new EJBException("Товар снят с продажи: " + product.getName());


            OrderProductEntity association = new OrderProductEntity();
            association.setOrderByOrderId(order);
            association.setProductByProductId(product);
            association.setCount(count);
            association.setPrice(price);
            em.persist(association);

            order.getOrderProductsById().add(association);
            product.getOrderProductsById().add(association);
        }

        orderBean.persist(order);

        for (CartEntity entity : cart) {
            removeProductFromCart(entity.getProductId());
        }
    }

    @Override
    public boolean canRemove(CartEntity entity) {
        // TODO Auto-generated method stub
        return true;
    }
}