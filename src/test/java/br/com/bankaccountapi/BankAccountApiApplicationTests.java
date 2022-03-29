package br.com.bankaccountapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.bankaccountapi.enums.Flag;
import br.com.bankaccountapi.enums.TypeCard;
import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.models.Card;
import br.com.bankaccountapi.models.Type;
import br.com.bankaccountapi.services.auth.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.junit.jupiter.api.Order;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BankAccountApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
;
    @Autowired
    private AuthService authService;

    private HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    public void setUp() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        JwtResponse jwt = authService.authenticateUser("henrique", "henrique123");
        headers.setBearerAuth(jwt.getToken());
    }


    @Order(1)
    @Test
    public void whenTrySigninWithInvalidcredentials_thenBadCredentialsShouldBeReturned() {

        HttpEntity<LoginRequest> httpEntity = new HttpEntity<LoginRequest>(
                new LoginRequest("henrique","henrique12"), headers);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/auth/signin/", httpEntity, String.class);

        assertTrue(response.getBody().contains("Bad credentials"));
        assertNotEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Order(2)
    @Test
    public void whenTrySigninWithValidcredentials_thenStatusOKShouldBeReturned() {

        HttpEntity httpEntity = new HttpEntity<LoginRequest>(new LoginRequest(
                "henrique", "henrique123"), headers);

        ResponseEntity<JwtResponse> response = restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/auth/signin/", httpEntity, JwtResponse.class);

        String token = response.getBody().getToken();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(token);
    }

    @Order(3)
    @Test
    public void whenTryRegisterUser_thenUserRegisteredSuccessfullyShouldBeReturned() {

        Set<String> roles = new HashSet<>();
        roles.add("USER");
        HttpEntity<SignupRequest> httpEntity = new HttpEntity<SignupRequest>
                (new SignupRequest("usuario_teste","teste123", roles), headers);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/auth/signup/", httpEntity, String.class);

        assertTrue(response.getBody().contains("User registered successfully"));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Order(4)
    @Test
    public void whenSearchAccountById_thenAccountShouldBeReturned() {

        HttpEntity httpEntity = new HttpEntity<LoginRequest>(new LoginRequest(
                "henrique", "henrique123"), headers);

        ResponseEntity<Account> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/accounts/1/", HttpMethod.GET, httpEntity, Account.class);

        assertEquals(response.getBody().getNameOwner(), "Henrique Barros");
    }

    @Order(5)
    @Test
    public void whenTrySaveAccount_thenAccountShouldBeSaveAndReturned() {

        HttpEntity<Account> httpAccount = new HttpEntity<Account>
                (new Account("Name test", "1234",
                        "12345678", "0", null), headers);

        ResponseEntity<Account> response = restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/accounts/", httpAccount, Account.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody().getId());
        assertEquals(response.getBody().getNameOwner(), "Name test");
    }

    @Order(6)
    @Test
    public void shouldUpdateAccount() {

        HttpEntity<Account> httpAccount = new HttpEntity<Account>
                (new Account("Name test changed", "4567",
                        "87654321", "1", null), headers);

        restTemplate.put("http://localhost:" + port + "/api/v1/accounts/edit/2", httpAccount, "id");

        ResponseEntity<Account> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/accounts/2", HttpMethod.GET, httpAccount, Account.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), 2);
        assertEquals(response.getBody().getNameOwner(), "Name test changed");
        assertEquals(response.getBody().getAccountCode(), "87654321");
    }

}
