package dev.redbeans.bootcamp202506eirzarog.springapi;

import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import dev.redbeans.bootcamp202506eirzarog.springapi.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = User .class) // Ensure that the User class is scanned for JPA entities
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApiApplication.class, args);
    }

}
