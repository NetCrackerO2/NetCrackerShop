package models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders", schema = "public", catalog = "shopDB")
public class OrderEntity {
    private int id;
    private String addres;
    private Date date;
    private String status;
    private List<OrderProductEntity> orderProductsById;
    private ClientEntity clientByClientId;
    private List<ProductEntity> products = new ArrayList<ProductEntity>(0);

    @Id
    @SequenceGenerator(name = "orders_id_seq",
            sequenceName = "orders_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "orders_id_seq")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "addres")
    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (addres != null ? !addres.equals(that.addres) : that.addres != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (addres != null ? addres.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @XmlTransient
    @OneToMany(mappedBy = "orderByOrderId")
    public List<OrderProductEntity> getOrderProductsById() {
        return orderProductsById;
    }

    public void setOrderProductsById(List<OrderProductEntity> orderProductsById) {
        this.orderProductsById = orderProductsById;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }


    /*TODO: додумать
    Эта связь многие-ко-многим работает работает наполовину: в таблице order_product заполняет только
    поля order_id и product_id. Остальные поля корректно заполняются, только если заполняется
    и сохраняется объект OrderProductEntity (как в методе addProduct).
     */
    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<ProductEntity> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }


    public void addProduct(ProductEntity product, int count, float price) {
        OrderProductEntity association = new OrderProductEntity();
        association.setOrderByOrderId(this);
        association.setProductByProductId(product);
        association.setCount(count);
        association.setPrice(price);

        if (this.orderProductsById == null)
            this.orderProductsById = new ArrayList<>();

        this.orderProductsById.add(association);
        product.getOrderProductsById().add(association);
    }
}
