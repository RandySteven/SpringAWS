package com.example.demo.controllers;

import com.example.demo.entity.models.Song;
import com.example.demo.interfaces.actions.SongAction;
import com.example.demo.interfaces.services.implementations.ArtistServiceImpl;
import com.example.demo.interfaces.services.implementations.SongServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/songs")
public class SongController implements SongAction {

    @Autowired
    private SongServiceImpl songService;

    private Map<String, Object> responseMap;

    private ResponseEntity<Map<String, Object>> responseEntity;

    private static final Logger LOGGER = LoggerFactory.getLogger(SongController.class);

    private static final String GET_ALL_SONGS_ENDPOINT = "/";
    private static final String POST_SONG_ENDPOINT = "/";
    private static final String PUT_SONG_BY_ID_ENDPOINT = "/update-song/{songId}";
    private static final String DELETE_SONG_BY_ID_ENDPOINT = "/delete-song/{songId}";
    private static final String GET_SONG_BY_ID_ENDPOINT = "/{songId}";

    @Override
    @PostMapping(POST_SONG_ENDPOINT)
    public ResponseEntity<Map<String, Object>> postSong(Song song) {
        LOGGER.info("============= Post Song =============");
        responseMap = songService.post(song);
        LOGGER.info("================= Response : " + responseMap);
        responseEntity = ResponseEntity.ok(responseMap);
        return responseEntity;
    }

    @Override
    @GetMapping(GET_ALL_SONGS_ENDPOINT)
    public ResponseEntity<Map<String, Object>> getAllSongs() {
        LOGGER.info("============= Get All Songs =============");
        responseMap = songService.get();
        responseEntity = ResponseEntity.ok(responseMap);
        return responseEntity;
    }

    @Override
    @GetMapping(GET_SONG_BY_ID_ENDPOINT)
    public ResponseEntity<Map<String, Object>> getSongById(String songId) {
        LOGGER.info("============= Get Song By Id =============");
        responseMap = songService.getById(songId);
        if(responseMap.get("responseCode").equals(404)){
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }else{
            responseEntity = ResponseEntity.ok(responseMap);
        }
        return responseEntity;
    }

    @Override
    @PutMapping(PUT_SONG_BY_ID_ENDPOINT)
    public ResponseEntity<Map<String, Object>> putSongById(String songId, Song song) {
        LOGGER.info("============= Put Song =============");
        responseMap = songService.updateById(songId, song);
        if(responseMap.get("responseCode").equals(404)){
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }else{
            responseEntity = ResponseEntity.ok(responseMap);
        }
        return responseEntity;
    }

    @Override
    @DeleteMapping(DELETE_SONG_BY_ID_ENDPOINT)
    public ResponseEntity<Map<String, Object>> deleteSongById(String songId) {
        LOGGER.info("============= Delete Song =============");
        responseMap = songService.deleteById(songId);
        if(responseMap.get("responseCode").equals(404)){
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }else{
            responseEntity = ResponseEntity.ok(responseMap);
        }
        return responseEntity;
    }
}
