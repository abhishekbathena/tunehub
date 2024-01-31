package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entites.Song;
import com.example.demo.servies.SongService;


@Controller
public class SongController {
 
	@Autowired
	SongService service;
	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song)
	{
		boolean songstatus=service.songExists(song.getName());
		if(songstatus==false)
		{
		service.addSong(song);
		System.out.println("Song added succesfully");
		}
		else System.out.println("song aleady exists");
		return "adminhome";
	}
	@GetMapping("/viewsongs")
	public String viewSongs(Model model)
	{
		List<Song>songLists=service.fetchAllSongs();
		model.addAttribute("songs",songLists);
		return "displaySongs";
	}
	@GetMapping("/playsong/{name}")
	public String playsong(Model model,@PathVariable String name)
	{
		Song songplay=service.getSong(name);
		model.addAttribute("song",songplay);
		return "PlaySong";
	}
	
	@GetMapping("/playsongs")
	public String playSongs(Model model)
	{
		boolean premiumUser=false;
		if(premiumUser==true)
		{
			List<Song>songLists=service.fetchAllSongs();
			model.addAttribute("songs",songLists);
			return "displaySongs";
		}
		else
		{
			return "makePayment";
		}
	}
	
	
	
}
