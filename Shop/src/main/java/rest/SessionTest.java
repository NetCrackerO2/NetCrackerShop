package rest;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class SessionTest implements Serializable {
    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    int test = 0;
}
