package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entites.PlayList;
import com.example.demo.entites.Song;
import com.example.demo.servies.PlaylistService;
import com.example.demo.servies.SongService;

@Controller
public class PlaylistController {
	
	
	@Autowired
	SongService songService;
	
	
	@Autowired
	PlaylistService playlistservice;
	
	
  @GetMapping("/createPlaylist")
  public String createPlaylist(Model model)
  {
	  List<Song> songList=songService.fetchAllSongs();
	  model.addAttribute("songs",songList);
	  return "createPlaylist";
  }
  
  @PostMapping("/addPlaylist")
  public String addPlaylist(@ModelAttribute PlayList playlist)
  {
	  //updating song table
	  playlistservice.addPlaylist(playlist);
	  
	  //updating song table
	  List<Song> songList=playlist.getSongs();
	  for(Song s:songList)
	  {
		  s.getPlaylists().add(playlist);
		  songService.updateSong(s);
	  }
	  return "adminhome";
  }
  
  @GetMapping("/viewPlaylists")
  public String viewPlaylists(Model model) {
	  
	  List<PlayList>allplaylists=playlistservice.fetchAllPlaylists();
	  model.addAttribute("allPlaylists", allplaylists);
	  
      return "displayPlaylists";
  }
  
  
}
