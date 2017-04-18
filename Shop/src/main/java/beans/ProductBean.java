package beans;


import models.*;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Stateless
public class ProductBean extends GenericBean<ProductEntity> {
    @Inject
    CategoryBean categoryBean;
    @Inject
    ProductBean productBean;
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
        } catch (EntityNotFoundException e) {
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

    public ProductEntity addProduct(String name,
                                    String description,
                                    int count,
                                    float price,
                                    int categoryId) throws EJBException {
        ProductEntity product = new ProductEntity();

        if (name.equals("")) {
            throw new EJBException("Недопустимое имя клиента.");
        }
        product.setName(name);

        product.setDescription(description);

        if (count < 0) {
            throw new EJBException("Недопустимое количество товара.");
        }
        product.setCount(count);

        if (price < 0) {
            throw new EJBException("Недопустимая стоимость товара.");
        }
        product.setPrice(price);

        CategoryEntity category = categoryBean.get(categoryId);
        if (category == null) {
            throw new EJBException("Такой категории не существует! Id = " + categoryId);
        }
        product.setCategoryByCategoryId(category);
        category.getProductsById().add(product);

        return persist(product);
    }

    //Фильтрация списка товаров по имени
    public List<ProductEntity> filterProductByName(String name,List<ProductEntity> list) {
        list=list.stream().filter(s->(s.getName().contains(name))).collect(Collectors.toList());
        return list;
    }
    //Фильтрация списка товаров по категориями
    public List<ProductEntity> filterProductByCategory(String category,List<ProductEntity> list){
        list=list.stream().filter(s->(s.getCategoryByCategoryId().getName().equals(category))).collect(Collectors.toList());
        return list;
    }

    //Фильтрация списка товаров по диапазону цен
    public List<ProductEntity> filterProductByPrice(int priceMin, int priceMax,List<ProductEntity> list){
        list=list.stream().filter(s->(s.getPrice()>=priceMin && s.getPrice()<=priceMax)).collect(Collectors.toList());
        return list;
    }

    //Фильтрация списка товаров по кол-ву товара на складе
    public List<ProductEntity> filterByCount(int count, List<ProductEntity> list){
        list=list.stream().filter(s->(s.getCount()>=count)).collect(Collectors.toList());
        return list;
    }
}