package beans;


import clientInfo.AdminInterceptor;
import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAdmin;
import models.*;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@Stateless
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
public class ProductBean extends GenericBean<ProductEntity> {
    private static final int MAX_PRICE = 999999999;

    @Inject
    CategoryBean categoryBean;
    @Inject
    ProductBean productBean;
    @Inject
    ClientInfo clientInfo;

    @Override
    protected Class<ProductEntity> getEntityClass() {
        return ProductEntity.class;
    }


    // Получение списка товаров определённой категории
    public List<ProductEntity> getByCategory(int categoryId) {
        try {
            List<ProductEntity> list = em.createQuery("SELECT e from CategoryEntity e where e.id=:token",
                                                      CategoryEntity.class)
                                         .setParameter("token", categoryId)
                                         .getSingleResult()
                                         .getProductsById();
            list.size();
            return list;
        } catch (EntityNotFoundException | NoResultException e) {
            return null;
        }
    }

    // Получение содержимого заказа
    public List<ProductInOrder> getInOrder(int orderId) {
        OrderEntity order;
        try {
            order = em.createQuery("SELECT e from OrderEntity e where e.id=:token", OrderEntity.class)
                      .setParameter("token", orderId)
                      .getSingleResult();
        } catch (EntityNotFoundException e) {
            return null;
        }
        if (order == null) {
            return null;
        }

        List<ProductEntity> products = order.getProducts();
        List<OrderProductEntity> orderProductsById = order.getOrderProductsById();

        List<ProductInOrder> productsInOrder = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productsInOrder.add(new ProductInOrder());
            productsInOrder.get(i).setId(products.get(i).getId());
            productsInOrder.get(i).setName(products.get(i).getName());
            productsInOrder.get(i).setPrice(products.get(i).getPrice());
            productsInOrder.get(i).setDescription(products.get(i).getDescription());
            productsInOrder.get(i).setShoppingCount(orderProductsById.get(i).getCount());
            productsInOrder.get(i).setShoppingPrice(orderProductsById.get(i).getPrice());
        }

        return productsInOrder;
    }

    @NeedAdmin
    public ProductEntity addProduct(String name,
                                    String description,
                                    int count,
                                    BigDecimal price,
                                    int categoryId,
                                    boolean disabled) throws EJBException {
        ProductEntity product = new ProductEntity();

        if (name.equals("")) {
            throw new EJBException("Недопустимое пустое название товара.");
        }
        product.setName(name);

        product.setDescription(description);

        if (count < 0) {
            throw new EJBException("Недопустимое количество товара: " + count);
        }
        product.setCount(count);

        checkPrice(price);
        product.setPrice(price);

        CategoryEntity category = categoryBean.get(categoryId);
        if (category == null) {
            throw new EJBException("Такой категории не существует! Id = " + categoryId);
        }
        product.setCategory(category);
        category.getProductsById().add(product);

        product.setDisabled(disabled);

        return persist(product);
    }

    @NeedAdmin
    public void editProduct(int id,
                            String name,
                            String description,
                            int count,
                            BigDecimal price,
                            int categoryId,
                            boolean disabled) {
        ProductEntity productEntity = get(id);

        if (productEntity == null) {
            return;
        }
        ProductEntity duplicate;
        if ((duplicate = getByName(name)) != null && duplicate.getId() != id) {
            throw new EJBException("Продукт с таким названием уже существует: " + name);
        }

        checkPrice(price);

        productEntity.setName(name);
        productEntity.setDescription(description);
        productEntity.setCount(count);
        productEntity.setPrice(price);
        CategoryEntity category = categoryBean.get(categoryId);
        if (category == null) {
            throw new EJBException("Такой категории не существует! Id = " + categoryId);
        }
        productEntity.setCategory(category);
        productEntity.setDisabled(disabled);
    }

    private void checkPrice(BigDecimal price) {
        if (price.doubleValue() <= 0
                || price.doubleValue() > MAX_PRICE) {
            throw new EJBException("Недопустимая стоимость товара: " + price);
        }
    }

    @Override
    @NeedAdmin
    public void remove(int id) {
        super.remove(id);
    }

    @Override
    @NeedAdmin
    public boolean canRemove(ProductEntity entity) {
        boolean flag = true;

        try {
            flag = flag && em.createQuery("select e from OrderProductEntity e where e.productByProductId.id=:token",
                                          OrderProductEntity.class)
                             .setParameter("token", entity.getId())
                             .getResultList().size() == 0;
        } catch (EntityNotFoundException ignore) {
        }

        return flag;
    }

    public ProductEntity getByName(String name) {
        try {
            return em.createQuery("SELECT e from ProductEntity e where e.name=:token", ProductEntity.class)
                     .setParameter("token", name)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Integer getMaxCount() {
        List<ProductEntity> list;
        try {
            list = em.createQuery("SELECT e from ProductEntity e order by e.count desc", ProductEntity.class)
                     .setMaxResults(1)
                     .getResultList();
            list.size();
            return list.get(0).getCount();
        } catch (Exception e) {
            return 0;
        }
    }

    public BigDecimal getMaxPrice() {
        List<ProductEntity> list;
        try {
            list = em.createQuery("SELECT e from ProductEntity e order by e.price desc", ProductEntity.class)
                     .setMaxResults(1)
                     .getResultList();
            list.size();
            return list.get(0).getPrice();
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }
}