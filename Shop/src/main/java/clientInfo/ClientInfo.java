package clientInfo;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlElement;

@Named
@SessionScoped
public class ClientInfo {

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
