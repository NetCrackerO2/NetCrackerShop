package backup;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import models.CategoryEntity;
import models.ProductEntity;

@XmlRootElement(name = "backup")
public class BackupUnit {
    private List<CategoryEntity> categories;
    private List<ProductEntity> products;
    
    public List<CategoryEntity> getCategories(){
        return categories;
    }
    
    @XmlElementWrapper
    @XmlElement
    public void setCategories(List<CategoryEntity> categories){
        this.categories = categories;
    }
    
    public List<ProductEntity> getProducts(){
        return products;
    }
    
    @XmlElementWrapper
    @XmlElement
    public void setProducts(List<ProductEntity> products){
        this.products = products;
    }
}
