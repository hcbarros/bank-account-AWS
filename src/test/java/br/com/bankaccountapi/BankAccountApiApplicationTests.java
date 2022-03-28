package br.com.bankaccountapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.bankaccountapi.models.Account;
import br.com.bankaccountapi.services.auth.*;

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

    private HttpEntity<LoginRequest> httpEntity;


    @Order(1)
    @Test
    public void whenTrySigninWithInvalidcredentials_thenBadCredentialsShouldBeReturned() {

        setHttpEntity("henrique","henrique12");

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/auth/signin/", httpEntity, String.class);

        assertTrue(response.getBody().contains("Bad credentials"));
        assertNotEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Order(2)
    @Test
    public void whenTrySigninWithValidcredentials_thenStatusOKShouldBeReturned() {

        setHttpEntity("henrique","henrique123");

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
                (new SignupRequest("usuario_teste","teste123", roles), new HttpHeaders());

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/auth/signup/", httpEntity, String.class);

        assertTrue(response.getBody().contains("User registered successfully"));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Order(4)
    @Test
    public void whenSearchAccountById_thenAccountShouldBeReturned() {

        setHttpEntity("henrique","henrique123");

        ResponseEntity<Account> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/accounts/1/", HttpMethod.GET, httpEntity, Account.class);

        assertEquals(response.getBody().getNameOwner(), "Henrique Barros");
    }

    @Order(5)
    @Test
    public void whenTrySaveAccount_thenAccountShouldBeSaveAndReturned() {

        setHttpEntity("henrique","henrique123");

        ResponseEntity<Account> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/accounts/1/", HttpMethod.GET, httpEntity, Account.class);

        assertEquals(response.getBody().getNameOwner(), "Henrique Barros");
    }



    private void setHttpEntity(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JwtResponse jwt = authService.authenticateUser("henrique", "henrique123");
        headers.setBearerAuth(jwt.getToken());
        httpEntity = new HttpEntity<LoginRequest>(new LoginRequest(username, password), headers);
    }

}
