package clientInfo;


import javax.enterprise.context.SessionScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@SessionScoped
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ClientInfo implements Serializable {

    @XmlElement
    private Integer clientId;


    public ClientInfo() {

    }

    public ClientInfo(Integer clientId) {
        this.clientId = clientId;
    }

    public ClientInfo init(ClientInfo info) {
        if (info == null) {
            return this;
        }

        this.clientId = info.clientId;
        return this;
    }


    public boolean isLoggedIn() {
        return clientId != null;
    }

    public int getId() {
        return clientId;
    }

    public void setId(int id) {
        clientId = id;
    }
}
