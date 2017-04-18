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
    private String address;

    @XmlElement
    private String errorMessage = "";


    public ClientInfo() {

    }


    public ClientInfo init(ClientEntity client) {
        if (client == null) {
            return this;
        }

        setId(client.getId());
        setName(client.getName());
        setAddress(client.getDefaultAddress());

        return this;
    }


    public boolean isLoggedIn() {
        return id != null;
    }

    public void logout() {
        setId(null);
        setName(null);
        setAddress(null);
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
