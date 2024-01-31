package com.example.demo.servies;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entites.Users;
import com.example.demo.repositories.UsersRepository;


@Service
public class UsersServiceImplementation implements UsersService {
		
		@Autowired
		UsersRepository repo;
		@Override
		public String addUser(Users user)
		{
			repo.save(user);
			return "user added successfully";
		}
		
		
		
		@Override
		public boolean emailExists(String email)
		{
			
			if(repo.findByEmail(email)==null)
			{
				System.out.println("not found");
				return false;
				
			}
			else
			{
			System.out.println("found");
			return true;
			}
		}
		
		@Override
		public boolean userValidate(String email, String password) {
			// TODO Auto-generated method stub
			Users user=repo.findByEmail(email);
			if(password.equals(user.getPassword()))
			{
				return true;
			}
			else return false;
		}
		@Override
		public boolean roledupverify(String email, String role) {
			// TODO Auto-generated method stub
			Users user=repo.findByEmail(email);
			
			if(user!=null && role.equals(user.getRole())) return false;
			else return true;

		}
		
		@Override
		public Users getUser(String email) {
			// TODO Auto-generated method stub
			return repo.findByEmail(email);
		}
		@Override
		public void updateUser(Users user) {
			// TODO Auto-generated method stub
			repo.save(user);
			
		}
		@Override
		public String getrole(String email) {
			// TODO Auto-generated method stub
			Users us=repo.findByEmail(email);
			return us.getRole();
		}
}
