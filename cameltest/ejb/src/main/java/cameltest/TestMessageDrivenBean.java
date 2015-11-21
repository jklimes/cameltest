package cameltest;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@MessageDriven(name = "TestMessageDrivenEJB")
public class TestMessageDrivenBean implements javax.jms.MessageListener {
    public TestMessageDrivenBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            String text = ((TextMessage) message).getText();
            System.out.println("GOOD message received: " + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
