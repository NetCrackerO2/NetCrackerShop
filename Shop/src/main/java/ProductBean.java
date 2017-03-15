import models.ProductEntity;
import sql.ProductDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductBean {
    @Inject
    ProductDAO pdao;


    public ProductEntity create(String name, String description) {
        ProductEntity pe = new ProductEntity();
        pe.setProdName(name);
        pe.setDescription(description);
        return pdao.create(pe);
    }

    public List<ProductEntity> getAll() {
        return pdao.getAll();
    }

    public ProductEntity get(int id) {
        return pdao.get(id);
    }

}
