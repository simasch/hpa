package customer;

import java.io.Serializable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements Serializable {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
