package com.JWTTest.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWTTest.MyServices.UserServiceArea;

@RestController
@RequestMapping("/home")
public class HomeControllers {
	
	@Autowired
	
	UserServiceArea userServiceArea;
	
	
	@GetMapping("/users")
	
	public String getMessege() {
		
		System.out.println("gettomg users");
		return this.userServiceArea.myMessege();
	}
	
@GetMapping("/current-user")
	
	public String getUser(Principal principal) {
		
		System.out.println("gettomg users");
		return principal.getName();
	}
	

	

}
