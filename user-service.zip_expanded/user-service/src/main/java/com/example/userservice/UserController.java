package com.example.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping("/status/check")
	public String status() {
		return "users service working";
	}
	
	@PostMapping
	public String createUser(@RequestBody User user) {
		return "create user method is called" + user;
	}	
}
