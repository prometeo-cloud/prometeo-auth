package org.gatblau;

import org.eclipse.jetty.proxy.ProxyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class App {

    @Value("${DESTINATION_URL:https://docs.spring.io/}")
    private String _destinationURL;

    @Bean
    public ServletRegistrationBean proxyServlet(final ServletConfig dispatcherServlet) throws ServletException {
        ProxyServlet.Transparent proxyServlet = new ProxyServlet.Transparent();
        final Map<String, String> params = new HashMap<String, String>();
        params.put("proxyTo", _destinationURL);
        params.put("prefix", "/proxy");
        params.put("idleTimeout", "5000");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(proxyServlet, "/*");
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.setName("proxyServlet");
        servletRegistrationBean.setInitParameters(params);
        return servletRegistrationBean;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
