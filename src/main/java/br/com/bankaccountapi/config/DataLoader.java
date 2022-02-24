package br.com.bankaccountapi.config;

import br.com.bankaccountapi.enums.Flag;
import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.models.Card;
import br.com.bankaccountapi.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner baseLoad(AccountRepository repo) {

        return args -> {

            //String name, Flag flag, String number, String digitCode, BigDecimal limitBalance

            Card card = new Card("Henrique B", Flag.VISA,
                    "4568.1279. 2648.2965", "482", new BigDecimal("45682156921"));


            Account account = new Account("Henrique Barros", "1234",
                    "45678", "0", Arrays.asList(card));

            repo.save(account);
        };

    }

}