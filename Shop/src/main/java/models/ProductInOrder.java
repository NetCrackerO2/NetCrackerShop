package models;


import java.math.BigDecimal;

public class ProductInOrder {
    private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private BigDecimal shoppingPrice;
    private Integer shoppingCount;


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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(BigDecimal shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public Integer getShoppingCount() {
        return shoppingCount;
    }

    public void setShoppingCount(Integer shoppingCount) {
        this.shoppingCount = shoppingCount;
    }
}
