package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
// https://mp.weixin.qq.com/s/nOSddT-YPqwtNAzGGz0pvA
public class FileCopierWithCamel {
    public static void main(String[] args) throws Exception {
        // create  CamelContext
        CamelContext context = new DefaultCamelContext();
        // add our  route to the CamelContext
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("file:d:/inbox?noop=true").to("file:d:/outbox");
            }
        });
        // start the route and let it do its work
        context.start();
        Thread.sleep(10000);
        // stop the  CamelContext
        context.stop();
    }
}
