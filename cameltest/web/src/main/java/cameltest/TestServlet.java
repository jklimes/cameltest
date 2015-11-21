package cameltest;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestServlet")
public class TestServlet extends javax.servlet.http.HttpServlet {
    @EJB
    private TestSessionBean testSessionBean;

    @Resource(lookup = "java:/jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java:/jms/queue/TestQueue")
    private Queue queue;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String message = testSessionBean.identifyYourself();
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            final long start = System.currentTimeMillis();
            for (int i = 1; i <= 1000; i++) {
                TextMessage textMessage = session.createTextMessage("MSG" + i + " " + message);
                producer.send(textMessage);
            }
            final long time = System.currentTimeMillis() - start;
            connection.close();
            PrintWriter writer = response.getWriter();
            String answer = "Session bean message: " + message + ". All messages were sent. Sending messages took " + time + " ms";
            writer.append(answer);
            writer.flush();
            writer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
