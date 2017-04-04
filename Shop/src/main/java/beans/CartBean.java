package beans;


import models.CartEntity;
import models.OrderEntity;
import models.ProductEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.List;


@Named
@Stateless
public class CartBean extends GenericBean<CartEntity> {
    @Inject
    ProductBean productBean;

    @Inject
    OrderBean orderBean;

    @Override
    protected Class<CartEntity> getEntityClass() {
        return CartEntity.class;
    }

    // Возвращает наполнение корзины пользователя
    public List<CartEntity> getCart() {
        //TODO: брать аутентифицированного пользователя
        int clientId = 1;

        try {
            return em.createQuery("select e from CartEntity e where e.clientId=:token", CartEntity.class)
                    .setParameter("token", clientId)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    // Добавляет новый товар в корзину либо изменяет количество существующего
    public boolean addProductInCart(int productId, int count) {
        //TODO: брать аутентифицированного пользователя
        int clientId = 1;

        CartEntity cart;
        try {
            cart = em.createQuery("select e from CartEntity e where e.clientId=:token1 and e.productId=:token2", CartEntity.class)
                    .setParameter("token1", clientId)
                    .setParameter("token2", productId)
                    .getSingleResult();
        } catch (EntityNotFoundException | NoResultException e) {
            cart = new CartEntity();
            cart.setClientId(clientId);
            cart.setProductId(productId);
            cart.setCount(count);
            return persist(cart) != null;
        }

        cart.setCount(count);
        return true;
    }

    // Удаляет указанный товар из корзины пользователя, если он в ней есть
    public void removeProductFromCart(int productId) {
        //TODO: брать аутентифицированного пользователя
        int clientId = 1;

        CartEntity cart = em.createQuery("select e from CartEntity e where e.clientId=:token1 and e.productId=:token2", CartEntity.class)
                .setParameter("token1", clientId)
                .setParameter("token2", productId)
                .getSingleResult();

        if (cart != null)
            em.remove(cart);
    }

    // Оформляет заказ на основе корзины пользователя и очищает корзину
    public boolean createOrder() {
        //TODO: брать аутентифицированного пользователя
        int clientId = 1;
        String addres = "Testing addres";
        // TODO: deprecated объявление, не знаю, как надо нормально
        Date date = new Date(2017, 4, 13);

        OrderEntity order = new OrderEntity();
        order.setAddres(addres);
        order.setDate(date);

        List<CartEntity> cart = getCart();
        if (cart.size() == 0)
            return false;

        for (CartEntity entity : cart) {
            ProductEntity product = productBean.get(entity.getProductId());
            int count = entity.getCount();
            float price = product.getPrice();

            order.addProduct(product, count, price);
            removeProductFromCart(product.getId());
        }

        orderBean.persist(order);
        return true;
    }
}
