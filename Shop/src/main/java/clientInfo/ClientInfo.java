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
    private String errorMessage = "";


    public ClientInfo() {

    }


    public ClientInfo init(ClientEntity client) {
        if (client == null) {
            return this;
        }

        id = client.getId();

        return this;
    }


    public boolean isLoggedIn() {
        return id != null;
    }

    public void logout() {
        id = null;
    }

    public int getId() {
        return id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
