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

    @Value("${DESTINATION_URL:_}")
    private String _destinationURL;

    @Value("${URL_MAPPINGS:/*}")
    private String _urlMappings;

    @Value("${NO_PROXY:_}")
    private String _no_proxy;

    @Bean
    public ServletRegistrationBean proxyServlet(final ServletConfig dispatcherServlet) throws ServletException {
        if (!_no_proxy.equals("_")) {
            System.out.println(String.format("Setting NO_PROXY=%s", _no_proxy));
            System.setProperty("NO_PROXY", _no_proxy);
        }
        if (_destinationURL.equals("_")) {
            throw new RuntimeException("DESTINATION_URL has not been set.");
        }
        ProxyServlet.Transparent proxyServlet = new ProxyServlet.Transparent();
        final Map<String, String> params = new HashMap<String, String>();
        params.put("proxyTo", _destinationURL);
        params.put("idleTimeout", "5000");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(proxyServlet, _urlMappings);
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.setName("proxyServlet");
        servletRegistrationBean.setInitParameters(params);
        return servletRegistrationBean;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
