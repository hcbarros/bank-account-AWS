package br.com.bankaccountapi.services.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {

	@NotBlank(message = "The username must have some value!")
	@Size(min = 6, max = 100,
			message = "The username must have a minimum of 6 and a maximum of 100 characters!")
	private String username;

	@NotBlank(message = "User password must have some value!")
	@Size(min = 6, max = 100,
			message = "The password must have a minimum of 6 and a maximum of 100 characters!")
	private String password;
	
	public LoginRequest() { }

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
