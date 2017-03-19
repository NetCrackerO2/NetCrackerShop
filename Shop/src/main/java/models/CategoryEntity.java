package models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "categories", schema = "public", catalog = "shopDB")
public class CategoryEntity {
    private int id;
    private String name;
    private CategoryEntity categoryByParentId;

    @Id
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
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public CategoryEntity getCategoryByParentId() {
        return categoryByParentId;
    }

    public void setCategoryByParentId(CategoryEntity categoriesByParentId) {
        this.categoryByParentId = categoriesByParentId;
    }
}
