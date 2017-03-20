package models;


public class ProductInOrder {
    private int id;
    private String name;
    private Float price;
    private String description;
    private Float shoppingPrice;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(Float shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public Integer getShoppingCount() {
        return shoppingCount;
    }

    public void setShoppingCount(Integer shoppingCount) {
        this.shoppingCount = shoppingCount;
    }
}
