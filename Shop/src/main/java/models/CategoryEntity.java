package models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;


@XmlRootElement(name = "category")
@Entity
@Table(name = "categories", schema = "public", catalog = "shopDB")
public class CategoryEntity {
    private int id;
    private String name;
    private CategoryEntity categoryByParentId;
    private List<CategoryEntity> categoriesById;
    private List<ProductEntity> productsById;

    @Id
    @SequenceGenerator(name = "categories_id_seq",
            sequenceName = "categories_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "categories_id_seq")
    @Column(name = "id")
    public int getId() {
        return id;
    }
    
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public CategoryEntity getCategoryByParentId() {
        return categoryByParentId;
    }

    public void setCategoryByParentId(CategoryEntity categoryByParentId) {
        this.categoryByParentId = categoryByParentId;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryByParentId")
    public List<CategoryEntity> getCategoriesById() {
        return categoriesById;
    }

    public void setCategoriesById(List<CategoryEntity> categoriesById) {
        this.categoriesById = categoriesById;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public List<ProductEntity> getProductsById() {
        return productsById;
    }

    public void setProductsById(List<ProductEntity> productsById) {
        this.productsById = productsById;
    }
}
