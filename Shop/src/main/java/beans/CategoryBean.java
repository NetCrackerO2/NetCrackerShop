package beans;


import models.CategoryEntity;
import models.ProductEntity;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

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
            throw new EJBException("Недопустимое название категории.");
        }
        category.setName(name);

        if (parentCategoryId != null) {
            category.setCategoryByParentId(
                    get(parentCategoryId)
            );
        }

        return persist(category);
    }

    public void editCategory(int id,String name){
        CategoryEntity categoryEntity=get(id);

        if (categoryEntity==null){
            return;
        }

        categoryEntity.setName(name);
    }
    @Override
    public boolean canRemove(CategoryEntity entity) {
        boolean flag = true;

        try {
            flag = flag && em.createQuery("select e from ProductEntity e where e.category.id=:token",
                                          ProductEntity.class)
                             .setParameter("token", entity.getId())
                             .getResultList().size() == 0;
        } catch (EntityNotFoundException ignore) {
        }

        return flag;
    }
}
