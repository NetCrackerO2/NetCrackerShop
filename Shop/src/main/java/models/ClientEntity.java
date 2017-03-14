package models;

import javax.persistence.*;

/**
 * Created by temon137 on 14.03.17.
 */
@Entity
@Table(name = "client", schema = "public", catalog = "shop")
public class ClientEntity {
    private int id;
    private String clientName;
    private String defaultAddress;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "client_name")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (defaultAddress != null ? !defaultAddress.equals(that.defaultAddress) : that.defaultAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (defaultAddress != null ? defaultAddress.hashCode() : 0);
        return result;
    }
}
