package models;


import beans.CategoryBean;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;


@Entity
@Table(name = "products", schema = "public", catalog = "shopDB")
public class ProductEntity {
    @Inject
    CategoryBean categoryBean;

    private int id;
    private String name;
    private Float price;
    private Integer count;
    private String description;
    private Collection<OrderProductEntity> orderProductsById;
    //private int categoryId;
    private CategoryEntity category;

    @Id
    @SequenceGenerator(name = "products_id_seq",
            sequenceName = "products_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "products_id_seq")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count < 0) {
            throw new EJBException("Установка отрицательного количества товара невозможна.");
        }
        this.count = count;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productByProductId")
    public Collection<OrderProductEntity> getOrderProductsById() {
        return orderProductsById;
    }

    public void setOrderProductsById(Collection<OrderProductEntity> orderProductsById) {
        this.orderProductsById = orderProductsById;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public CategoryEntity getCategory() {
        if (category != null) {
            category.getName();
        }
        return category;
    }

    public void setCategory(CategoryEntity categoryByCategoryId) {
        this.category = categoryByCategoryId;
    }

    public int crutchGetCategoryId() {
        return getCategory().getId();
    }

    public void crutchSetCategoryId(int categoryId) {
        setCategory(categoryBean.get(categoryId));
    }
}
