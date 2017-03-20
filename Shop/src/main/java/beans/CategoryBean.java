package beans;


import DAO.CategoryDAO;
import models.CategoryEntity;
import models.ProductEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class CategoryBean {
    @Inject
    CategoryDAO dao;

    public List<CategoryEntity> getAll() {
        return dao.getAll();
    }
}
