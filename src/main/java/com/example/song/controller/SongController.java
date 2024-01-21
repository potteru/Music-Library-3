// Write your code here
package com.example.song.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.song.model.Song;

import com.example.song.service.SongJpaService;

@RestController
public class SongController {

    @Autowired
    SongJpaService songService;

    @GetMapping("/songs")
    public ArrayList<Song> get_Song_List() {

        return songService.getSongsList();
    }

    @PostMapping("/songs")
    public Song addSongs(@RequestBody Song song) {

        return songService.addSong(song);
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int songId) {

        return songService.getSongById(songId);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song) {

        return songService.updateSong(songId, song);
    }

    @DeleteMapping("/songs/{songId}")
    public ArrayList<Song> deleteSong(@PathVariable("songId") int songId) {

        songService.deleteSong(songId);
        return songService.getSongsList();
    }

}
