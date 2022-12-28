package test.task.service.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.task.service.entity.BankAccount;


@Configuration
public class Config {

    @Bean
    private SessionFactory getSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(BankAccount.class)
                .buildSessionFactory();
    }
}
