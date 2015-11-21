package cameltest;

import javax.ejb.Stateless;
import java.util.Date;

@Stateless(name = "TestSessionEJB")
public class TestSessionBean {
    public String identifyYourself() {
        return "Test Session EJB " + new Date();
    }
}

