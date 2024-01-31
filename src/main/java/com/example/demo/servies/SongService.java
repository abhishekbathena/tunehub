package com.example.demo.servies;

import com.example.demo.entites.Song;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SongService {

	public void addSong(Song song);

	public List<Song> fetchAllSongs();

	public boolean songExists(String name);

	public void updateSong(Song s);
	
	public Song getSong(String name);
		// TODO Auto-generated method stub
		
	

}
