package uk.co.mruoc.exercises.palindrome.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

    private Application() {
        // main application class
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
