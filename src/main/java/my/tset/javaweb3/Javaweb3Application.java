package my.tset.javaweb3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Javaweb3Application {

    public static void main(String[] args) {
        SpringApplication.run(Javaweb3Application.class, args);
    }

}
