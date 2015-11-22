package cameltest;

import org.apache.camel.Handler;

public class Bean {
    @Handler
    public void handle(String msg) {
        System.out.println("ahoj " + msg);
    }
}
