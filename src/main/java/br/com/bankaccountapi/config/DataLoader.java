package br.com.bankaccountapi.config;

import br.com.bankaccountapi.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner baseLoad(AccountRepository repo) {

        return args -> {

            

        };

    }

}