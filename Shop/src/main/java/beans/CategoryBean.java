package beans;


import models.CategoryEntity;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class CategoryBean extends GenericBean<CategoryEntity> {
    @Override
    protected Class<CategoryEntity> getEntityClass() {
        return CategoryEntity.class;
    }

    /*public List<ProductEntity> getProductsById(int id) {
        return get(id).getProductsById();
    }*/
}
