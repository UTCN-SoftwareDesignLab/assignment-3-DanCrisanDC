package clinicApp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages ={"clinicApp.model"})
@SpringBootApplication(scanBasePackages = {"clinicApp.application", "clinicApp.model", "clinicApp.repository", "clinicApp.service", "clinicApp.controller", "clinicApp.dto", "clinicApp.config"})
@EnableJpaRepositories(basePackages = {"clinicApp.repository"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}