package erp;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.ServletContextAware;

@SpringBootApplication
public class Application implements ServletContextAware {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean
                = new ServletRegistrationBean(servlet, "*.jsf");
        return servletRegistrationBean;
    }

    @Bean
    public OpenEntityManagerInViewFilter lazyLoadingFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
