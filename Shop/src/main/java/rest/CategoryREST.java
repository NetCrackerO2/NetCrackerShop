package rest;


import beans.CategoryBean;
import beans.GenericBean;
import models.CategoryEntity;

import javax.inject.Inject;
import javax.ws.rs.Path;


@Path("/category")
public class CategoryREST extends GenericREST<CategoryEntity> {
    @Inject
    CategoryBean bean;

    @Override
    protected GenericBean<CategoryEntity> getBean() {
        return bean;
    }
}
