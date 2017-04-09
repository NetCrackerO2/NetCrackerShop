package clientInfo;


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


    public ClientInfo() {

    }

//    public ClientInfo(Integer id) {
//        this.id = id;
//    }

    public ClientInfo init(ClientInfo info) {
        if (info == null) {
            return this;
        }

        this.id = info.id;
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
}
