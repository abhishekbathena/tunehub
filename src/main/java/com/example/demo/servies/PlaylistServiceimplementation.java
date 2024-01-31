package com.example.demo.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entites.PlayList;

import com.example.demo.repositories.PlaylistRepository;


@Service
public class PlaylistServiceimplementation implements PlaylistService {

	@Autowired
	PlaylistRepository repo;
	@Override
	public void addPlaylist(PlayList playlist) {
		// TODO Auto-generated method stub
		repo.save(playlist);
		
	}
	@Override
	public List<PlayList> fetchAllPlaylists() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
