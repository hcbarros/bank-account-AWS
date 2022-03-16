package br.com.bankaccountapi.services.auth;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	
	@NotBlank(message = "The username must have some value!")
	@Size(min = 6, max = 100, 
	message = "The username must have a minimum of 6 and a maximum of 100 characters!")
	private String username;
	
	@NotBlank(message = "User password must have some value!")
	@Size(min = 6, max = 100,
	message = "The password must have a minimum of 6 and a maximum of 100 characters!")
	private String password;

	private Set<String> roles;
	
	public SignupRequest() { }

	public SignupRequest(String username, String password, Set<String> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}


}
