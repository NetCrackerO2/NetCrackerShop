package rest;


import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
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
