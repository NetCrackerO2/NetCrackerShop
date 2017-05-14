package backup;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import models.CategoryEntity;
import models.UnregistredProductEntity;

@XmlRootElement(name = "backup")
@XmlSeeAlso({ UnregistredProductEntity.class, CategoryEntity.class })
public class BackupUnit {
    private List<CategoryEntity> categories;
    private List<UnregistredProductEntity> products;

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    @XmlElementWrapper
    @XmlElement
    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<UnregistredProductEntity> getProducts() {
        return products;
    }

    @XmlElementWrapper
    @XmlElement
    public void setProducts(List<UnregistredProductEntity> products) {
        this.products = products;
    }
}
