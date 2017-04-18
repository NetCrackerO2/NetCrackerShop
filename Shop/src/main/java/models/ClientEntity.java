package models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;


@Entity
@Table(name = "clients", schema = "public", catalog = "shopDB")
public class ClientEntity {
    private int id;
    private String name;
    private String defaultAddress;
    private List<OrderEntity> ordersById;

    @Id
    @SequenceGenerator(name = "clients_id_seq",
            sequenceName = "clients_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "clients_id_seq")
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

    @Basic
    @Column(name = "default_address")
    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (defaultAddress != null ? !defaultAddress.equals(that.defaultAddress) : that.defaultAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (defaultAddress != null ? defaultAddress.hashCode() : 0);
        return result;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientByClientId")
    public List<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(List<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
