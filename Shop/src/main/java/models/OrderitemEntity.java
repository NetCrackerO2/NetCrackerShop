package models;

import javax.persistence.*;

/**
 * Created by temon137 on 14.03.17.
 */
@Entity
@Table(name = "orderitem", schema = "public", catalog = "shop")
@IdClass(OrderitemEntityPK.class)
public class OrderitemEntity {
    private int id;
    private int ordId;
    private int itemId;
    private int count;
    private int price;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "ord_id")
    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    @Id
    @Column(name = "item_id")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderitemEntity that = (OrderitemEntity) o;

        if (id != that.id) return false;
        if (ordId != that.ordId) return false;
        if (itemId != that.itemId) return false;
        if (count != that.count) return false;
        if (price != that.price) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ordId;
        result = 31 * result + itemId;
        result = 31 * result + count;
        result = 31 * result + price;
        return result;
    }
}
