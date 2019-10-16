package com.example.userservice;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@NotNull(message = "firstname cannot be null")
	private String firstname;
	@NotNull(message = "lastname cannot be null")
	private String lastname;
	@NotNull(message = "password cannot be null")
	private String password;
	@NotNull(message = "email cannot be null")
	private String email;
	
}
