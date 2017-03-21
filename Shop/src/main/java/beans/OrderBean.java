package beans;


import DAO.OrderDAO;
import models.OrderEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class OrderBean {
    @Inject
    OrderDAO dao;

    public OrderEntity get(int id) {
        return dao.get(id);
    }

    public List<OrderEntity> getAll() {
        return dao.getAll();
    }

    public void addProductInOrder(int orderId, int productId, int count, float price) {
        dao.addProductInOrder(orderId, productId, count, price);
    }
}
