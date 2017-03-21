package models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;


@Entity
@Table(name = "categories", schema = "public", catalog = "shopDB")
public class CategoryEntity {
    private int id;
    private String name;
    private CategoryEntity categoryByParentId;
    private Collection<CategoryEntity> categoriesById;
    private Collection<ProductEntity> productsById;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public CategoryEntity getCategoryByParentId() {
        return categoryByParentId;
    }

    public void setCategoryByParentId(CategoryEntity categoryByParentId) {
        this.categoryByParentId = categoryByParentId;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categoryByParentId")
    public Collection<CategoryEntity> getCategoriesById() {
        return categoriesById;
    }

    public void setCategoriesById(Collection<CategoryEntity> categoriesById) {
        this.categoriesById = categoriesById;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categoryByCategoryId")
    public Collection<ProductEntity> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductEntity> productsById) {
        this.productsById = productsById;
    }
}
