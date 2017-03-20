package beans;


import DAO.ProductDAO;
import models.OrderProductEntity;
import models.ProductEntity;
import models.ProductInOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class ProductBean {
    @Inject
    ProductDAO dao;


    public ProductEntity create(String name, String description) {
        ProductEntity pe = new ProductEntity();
        pe.setName(name);
        pe.setDescription(description);
        return dao.create(pe);
    }

    public List<ProductEntity> getAll() {
        return dao.getAll();
    }

    public ProductEntity get(int id) {
        return dao.get(id);
    }

    public List<OrderProductEntity> getOrders(int id) {
        return dao.getOrders(id);
    }

    public void setDescription(int id, String description) {
        dao.get(id).setDescription(description);
    }


    public List<ProductEntity> getProductsByCategory(int id) {
        return dao.getProductsByCategory(id);
    }

    public List<ProductInOrder> getProductsInOrder(int orderId) {
        return dao.getProductsInOrder(orderId);
    }
}
