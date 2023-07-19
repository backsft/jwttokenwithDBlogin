package com.JWTTest.MyServices;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.JWTTest.MyEntities.Users;

@Service
public class UserService {

	private List<Users> store = new ArrayList<>();

	public UserService() {

		store.add(new Users(UUID.randomUUID().toString(), "Durgesh", "durgesh@dev.in"));
		store.add(new Users(UUID.randomUUID().toString(), "Harsh Tiwary", "harsh@dev.in"));
		store.add(new Users(UUID.randomUUID().toString(), "Ankit tiwary", "ankit@dev.in"));
		store.add(new Users(UUID.randomUUID().toString(), "Gautam Shukla", "gautam@dev.in"));

	}

	public List<Users> getUsers() {

		return this.store;
	}

}
