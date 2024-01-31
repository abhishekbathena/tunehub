package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.LoginData;
import com.example.demo.entites.Song;
import com.example.demo.entites.Users;
import com.example.demo.servies.SongService;
import com.example.demo.servies.UsersService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songservice;
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userstatus=service.emailExists(user.getEmail());
		boolean roleuserstatus=service.roledupverify(user.getEmail(), user.getRole());
		if(userstatus == false || roleuserstatus==true)
		{
			service.addUser(user);
			System.out.println("added");
		}
		else
		{
			System.out.println("already exits");
		
		}
		return "login";
		//TODO: process POST request
		
		
	}
	
	@PostMapping("/validate")
	public String userValidate(@RequestParam("email") String username,@RequestParam("password") String password ,HttpSession session,Model model)
	{
		System.out.println("react data received");
		
		if(service.userValidate(username, password)==true)
		{
			session.setAttribute("email",username);
			String role=service.getrole(username);
			if(role.equals("admin"))
			{
				return "adminhome";
			}
			else
			{
				Users user=service.getUser(username);
				
				boolean premiumStatus=user.isPremium();
				model.addAttribute("isPremium",premiumStatus);
				
				List<Song>songlist=songservice.fetchAllSongs();
				model.addAttribute("songs",songlist);
				
				return "customerHome";
			}	  
		}
		else
		{
			System.out.println("not valid");
			return "login";
		}
		
		
	}
	
//	@GetMapping("/pay")
//	public String pay(@RequestParam String email)
//	{
//		boolean paymentstatus=false; //payment api
//		
//		if(paymentstatus==true)
//		{
//			Users u = service.getUser(email);
//			u.setPremium(true);
//			service.updateUser(u);
//		}
//		
//		
//		return "login";
//		
//		
//	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
	
}
