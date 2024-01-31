package com.example.demo.servies;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entites.PlayList;

@Service
public interface PlaylistService {
	public void addPlaylist(PlayList playlist);

	public List<PlayList> fetchAllPlaylists();
}
