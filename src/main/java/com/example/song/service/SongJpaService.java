// Write your code here
package com.example.song.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.song.model.Song;
import com.example.song.repository.SongJpaRepository;
import com.example.song.repository.SongRepository;

@Service
public class SongJpaService implements SongRepository {

	@Autowired
	private SongJpaRepository songJpaRepository;

	@Override
	public ArrayList<Song> getSongsList() {
		Collection<Song> songList = songJpaRepository.findAll();
		ArrayList<Song> arrList_of_songs = new ArrayList<>(songList);
		return arrList_of_songs;
	}

	@Override
	public Song addSong(Song song) {

		Song savedSong = songJpaRepository.save(song);
		return savedSong;
	}

	@Override
	public Song getSongById(int songId) {
		try {
			Song song = songJpaRepository.findById(songId).get();
			return song;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Song updateSong(int songId, Song song) {

		try {
			Song newSong = songJpaRepository.findById(songId).get();
			if (song.getSongName() != null) {
				newSong.setSongName(song.getSongName());
			}
			if (song.getLyricist() != null) {
				newSong.setLyricist(song.getLyricist());
			}
			if (song.getSinger() != null) {
				newSong.setSinger(song.getSinger());
			}
			if (song.getMusicDirector() != null) {
				newSong.setMusicDirector(song.getMusicDirector());
			}
			songJpaRepository.save(newSong);
			return getSongById(songId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deleteSong(int songId) {
		try {
			songJpaRepository.deleteById(songId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
