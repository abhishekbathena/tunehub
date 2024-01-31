package com.example.demo.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entites.Song;
import com.example.demo.repositories.SongRepository;
@Service
public class SongServiceimplementation implements SongService{
	@Autowired
	SongRepository repo;
	@Override
	public void addSong(Song song)
	{
		repo.save(song);
	}
	@Override
	public List<Song> fetchAllSongs() {
		// TODO Auto-generated method stub
	     return repo.findAll();

	}
	@Override
	public boolean songExists(String name) {
		// TODO Auto-generated method stub
		Song song=repo.findByName(name);
		if(song==null) return false;
		else return true;
	}
	@Override
	public void updateSong(Song s) {
		repo.save(s);
		// TODO Auto-generated method stub
	}
	@Override
	public Song getSong(String name) {
		// TODO Auto-generated method stub
		Song song=repo.findByName(name);
		
		return song;
	}
}
