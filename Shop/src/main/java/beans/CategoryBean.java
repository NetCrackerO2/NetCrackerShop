package beans;


import models.CategoryEntity;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class CategoryBean extends GenericBean<CategoryEntity> {
    @Override
    protected Class<CategoryEntity> getEntityClass() {
        return CategoryEntity.class;
    }

    public CategoryEntity addCategory(String name,
                                      Integer parentCategoryId) throws EJBException {
        CategoryEntity category = new CategoryEntity();

        if (name.equals("")) {
            throw new EJBException("Недопустимое имя категории.");
        }
        category.setName(name);

        if (parentCategoryId != null) {
            category.setCategoryByParentId(
                    get(parentCategoryId)
            );
        }

        return persist(category);
    }
}
