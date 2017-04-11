package beans;


import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;
import models.CartEntity;
import models.OrderEntity;
import models.ProductEntity;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Named
@Stateless
@Interceptors(AuthorizationInterceptor.class)
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
            cart = em.createQuery("select e from CartEntity e where e.clientId=:token1 and e.productId=:token2", CartEntity.class)
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
            cart = em.createQuery("select e from CartEntity e where e.clientId=:token1 and e.productId=:token2", CartEntity.class)
                    .setParameter("token1", clientInfo.getId())
                    .setParameter("token2", productId)
                    .getSingleResult();
        } catch (EntityNotFoundException | NoResultException e) {
            return;
        }

        em.remove(cart);
    }

    // Оформляет заказ на основе корзины пользователя и очищает корзину
    public void createOrder(String addres) {
        List<CartEntity> cart = getCart();
        if (cart.size() == 0) {
            throw new EJBException("Корзина пуста");
        }

        if (addres == null || Objects.equals(addres, "")) {
            addres = clientInfo.getAddres();
        }

        java.util.Date currentDate = new java.util.Date();
        Date date = new Date(currentDate.getTime());

        OrderEntity order = new OrderEntity();
        order.setClientByClientId(clientBean.get(clientInfo.getId()));
        order.setAddres(addres);
        order.setDate(date);
        orderBean.persist(order);

        for (CartEntity entity : cart) {
            ProductEntity product = productBean.get(entity.getProductId());
            if (product.getCount() < entity.getCount()) {
                throw new EJBException("Нет в наличии необходимого количества "
                        + product.getName()
                        + ": есть "
                        + product.getCount()
                        + " шт., а надо "
                        + entity.getCount()
                        + " шт."
                );
            }

            int count = entity.getCount();
            float price = product.getPrice();

            order.addProduct(product, count, price);
        }

        for (CartEntity entity : cart) {
            removeProductFromCart(entity.getProductId());
        }
    }
}