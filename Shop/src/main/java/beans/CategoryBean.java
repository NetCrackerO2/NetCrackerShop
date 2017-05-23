package beans;


import clientInfo.AdminInterceptor;
import clientInfo.AuthorizationInterceptor;
import clientInfo.NeedAdmin;
import models.CategoryEntity;
import models.ProductEntity;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

@Named
@Stateless
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
public class CategoryBean extends GenericBean<CategoryEntity> {
    private static final int NAME_MAX_LENGTH = 16;

    @Override
    protected Class<CategoryEntity> getEntityClass() {
        return CategoryEntity.class;
    }

    @NeedAdmin
    public CategoryEntity addCategory(String name,
                                      Integer parentCategoryId) throws EJBException {
        CategoryEntity category = new CategoryEntity();

        if (name.equals("")) {
            throw new EJBException("Недопустимое пустое название категории.");
        }
        checkName(name);
        category.setName(name);

        if (parentCategoryId != null) {
            category.setCategoryByParentId(get(parentCategoryId));
        }

        return persist(category);
    }

    @NeedAdmin
    public void editCategory(int id, String name) {
        CategoryEntity categoryEntity = get(id);

        if (categoryEntity == null) {
            return;
        }
        CategoryEntity duplicate;
        if ((duplicate = getByName(name)) != null && duplicate.getId() != id) {
            throw new EJBException("Категория с таким названием уже существует: " + name);
        }
        checkName(name);

        categoryEntity.setName(name);
    }

    @Override
    @NeedAdmin
    public void remove(int id) {
        super.remove(id);
    }

    @Override
    @NeedAdmin
    public boolean canRemove(CategoryEntity entity) {
        boolean flag = true;

        try {
            flag = flag
                    && em.createQuery("select e from ProductEntity e where e.category.id=:token", ProductEntity.class)
                         .setParameter("token", entity.getId()).getResultList().size() == 0;
        } catch (EntityNotFoundException ignore) {
        }

        return flag;
    }

    public CategoryEntity getByName(String name) {
        try {
            return em.createQuery("SELECT e from CategoryEntity e where e.name=:token", CategoryEntity.class)
                     .setParameter("token", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private void checkName(String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new EJBException("Название должно быть не длиннее 16 символов.");
        }
    }
}
