package com.example.accountmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private Environment env;
	@GetMapping("/status/check")
	public String status() {
		return "account service working on "+ env.getProperty("local.server.port");
	}
}