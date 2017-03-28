package rest;


import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;


@Named
@Stateful
@SessionScoped
public class SessionTest {
    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    int test = 0;
}
