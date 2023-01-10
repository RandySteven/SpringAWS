package com.example.demo.interfaces.actions;

import com.example.demo.entity.models.Artist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface ArtistAction {
    ResponseEntity<Map<String, Object>> getArtistByArtistId(@PathVariable String artistId);
    ResponseEntity<Map<String, Object>> getAllArtists();
    ResponseEntity<Map<String, Object>> postArtist(@RequestBody Artist artist);
    ResponseEntity<Map<String, Object>> putArtist(@PathVariable String artistId, @RequestBody Artist artist);
    ResponseEntity<Map<String, Object>> deleteArtist(@PathVariable String artistId);
}
