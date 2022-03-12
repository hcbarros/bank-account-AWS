package br.com.bankaccountapi.config;

import br.com.bankaccountapi.enums.Flag;
import br.com.bankaccountapi.enums.TypeCard;
import br.com.bankaccountapi.models.*;
import br.com.bankaccountapi.repositories.AccountRepository;
import br.com.bankaccountapi.repositories.RoleRepository;
import br.com.bankaccountapi.repositories.UserRepository;
import br.com.bankaccountapi.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataLoader {

    @Autowired
    private AuthService authService;

    @Bean
    CommandLineRunner baseLoad(AccountRepository accountRepository,
                               RoleRepository roleRepository) {

        return args -> {

            Card card = new Card("Henrique B", Flag.VISA, new Type(TypeCard.CREDIT_CARD),
                    "4568.1279. 2648.2965", "482", new BigDecimal("45682156921"));

            Account account = new Account("Henrique Barros", "1234",
                    "45678458", "0", Arrays.asList(card));

            accountRepository.save(account);

            Set<Role> roles = new HashSet<>();
            roles.addAll(Arrays.asList(new Role(ERole.USER), new Role(ERole.ADMIN)));

            roleRepository.saveAll(roles);


            Set<String> roleStrings= new HashSet<>();
            roleStrings.add(ERole.USER.name());
            roleStrings.add(ERole.ADMIN.name());

            authService.registerUser("henrique","henrique123", roleStrings);
        };

    }

}