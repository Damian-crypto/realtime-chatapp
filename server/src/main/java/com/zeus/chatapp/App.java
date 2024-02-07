package com.zeus.chatapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zeus.chatapp.repository.UserRepository;

// Entry point to the application
@SpringBootApplication
// EnableJpaRepositories annotation is used to enable these repositories and configure their behavior.
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("All loaded beans in spring context:");
            for (String beanName : ctx.getBeanDefinitionNames()) {
                System.out.println("Bean: " + beanName);
            }
        };
    }
}
