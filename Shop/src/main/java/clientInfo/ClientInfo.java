package clientInfo;


import models.ClientEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;


@Named
@SessionScoped
public class ClientInfo implements Serializable {

    @XmlElement
    private Integer id;

    @XmlElement
    private String name;

    @XmlElement
    private String addres;


    public ClientInfo() {

    }


    public ClientInfo init(ClientEntity client) {
        if (client == null) {
            return this;
        }

        setId(client.getId());
        setName(client.getName());
        setAddres(client.getDefaultAddress());

        return this;
    }


    public boolean isLoggedIn() {
        return id != null;
    }

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

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
}
