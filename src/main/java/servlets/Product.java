package servlets;

public class Product {
    private String title;
    private String description;
    private double price;
    private int count;

    public Product(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Product(String title, String description, double price, int count) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private int getCount(){ return  count; }

    private void setCount(int count){ this.count = count; }
}
