package models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by temon137 on 14.03.17.
 */
public class OrderitemEntityPK implements Serializable {
    private int id;
    private int ordId;
    private int itemId;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ord_id")
    @Id
    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    @Column(name = "item_id")
    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderitemEntityPK that = (OrderitemEntityPK) o;

        if (id != that.id) return false;
        if (ordId != that.ordId) return false;
        if (itemId != that.itemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ordId;
        result = 31 * result + itemId;
        return result;
    }
}
