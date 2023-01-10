package com.example.demo.interfaces.actions;

import com.example.demo.entity.models.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface SongAction {
    ResponseEntity<Map<String, Object>> postSong(@RequestBody Song song);
    ResponseEntity<Map<String, Object>> getAllSongs();
    ResponseEntity<Map<String, Object>> getSongById(@PathVariable String songId);
    ResponseEntity<Map<String, Object>> putSongById(@PathVariable String songId, @RequestBody Song song);
    ResponseEntity<Map<String, Object>> deleteSongById(@PathVariable String songId);
}
