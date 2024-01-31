package com.example.demo.servies;

import org.springframework.stereotype.Service;


import com.example.demo.entites.Users;

@Service
public interface UsersService {
	public String addUser(Users user);
	public boolean emailExists(String email);
	public boolean userValidate(String email,String password);
	public boolean roledupverify(String email,String role);
	public String getrole(String email);
	public Users getUser(String email);
	
	public void updateUser(Users user);
	
}
