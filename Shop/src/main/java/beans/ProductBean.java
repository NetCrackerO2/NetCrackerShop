package beans;


import models.OrderEntity;
import models.ProductEntity;
import DAO.ProductDAO;

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

    public List<OrderEntity> getOrders(int id) {
        return dao.getOrders(id);
    }

    public void setDescription(int id, String descriprion) {
        dao.get(id).setDescription(descriprion);
    }
}
