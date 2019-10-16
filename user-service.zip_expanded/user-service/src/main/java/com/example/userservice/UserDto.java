package com.example.userservice;

import java.io.Serializable;

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
public class UserDto implements Serializable{
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;
}
