package uk.starling.techchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "uk.starling.techchallenge")
public class RoundUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoundUpApplication.class, args);
    }

}
