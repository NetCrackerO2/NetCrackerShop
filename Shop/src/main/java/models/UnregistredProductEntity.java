package models;

import javax.ejb.EJBException;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.xml.bind.annotation.XmlRootElement;

import beans.CategoryBean;

@XmlRootElement(name = "product")
public class UnregistredProductEntity {
    private int id;
    private String name;
    private Float price;
    private Integer count;
    private String description;
    private int categoryId;

    public UnregistredProductEntity() {
    }

    public UnregistredProductEntity(ProductEntity original) {
        id = original.getId();
        name = original.getName();
        price = original.getPrice();
        count = original.getCount();
        description = original.getDescription();
        categoryId = original.getCategory() == null ? -1 : original.getCategory().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count < 0) {
            throw new EJBException("Установка отрицательного количества товара невозможна.");
        }
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int id) {
        this.categoryId = id;
    }

    public ProductEntity toRegistred() {
        ProductEntity result = new ProductEntity();
        result.setId(getId());
        result.setName(getName());
        result.setPrice(getPrice());
        result.setCount(getCount());
        result.setDescription(getDescription());
        if (getCategoryId() == -1)
            result.setCategory(null);
        else {
            BeanManager bm = CDI.current().getBeanManager();
            Bean<CategoryBean> bean = (Bean<CategoryBean>) bm.getBeans(CategoryBean.class).iterator().next();
            CreationalContext<CategoryBean> ctx = bm.createCreationalContext(bean);
            CategoryBean categoryBean = (CategoryBean) bm.getReference(bean, CategoryBean.class, ctx);
            result.setCategory(categoryBean.get(getCategoryId()));
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UnregistredProductEntity that = (UnregistredProductEntity) o;

        if (id != that.id)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null)
            return false;
        if (count != null ? !count.equals(that.count) : that.count != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;

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
}
