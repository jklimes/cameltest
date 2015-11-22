package cameltest;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloCamel {
    public static void main(String[] args) throws Exception {
        CamelContext ctx = new DefaultCamelContext();
        RouteBuilder builder = new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("class:cameltest.Bean")
                        .to("mock:stop");
            }
        };
        ctx.addRoutes(builder);
        ctx.start();
        ProducerTemplate template = ctx.createProducerTemplate();
        template.sendBody("direct:start", "test");
        ctx.stop();
    }
}
