package com.example.demo.controllers;

import com.example.demo.entity.models.Artist;
import com.example.demo.interfaces.actions.ArtistAction;
import com.example.demo.interfaces.services.ArtistService;
import com.example.demo.interfaces.services.implementations.ArtistServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/artists")
public class ArtistController implements ArtistAction {

    @Autowired
    private ArtistServiceImpl artistService;

    private static final String POST_ARTIST_ENDPOINT = "/add-artist";
    private static final String GET_ARTISTS_ENDPOINT = "/";
    private static final String GET_ARTIST_BY_ID_ENDPOINT = "/{artistId}";
    private static final String PUT_ARTIST_BY_ID_ENDPOINT = "/update-artist/{artistId}";
    private static final String DELETE_ARTIST_BY_ID_ENDPOINT = "/delete-artist/{artistId}";

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);

    private ResponseEntity<Map<String, Object>> responseEntity;

    private Map<String, Object> responseMap;

    @Override
    @GetMapping(GET_ARTIST_BY_ID_ENDPOINT)
    public ResponseEntity<Map<String, Object>> getArtistByArtistId(@PathVariable String artistId){
        responseMap = artistService.getById(artistId);
        if(responseMap.get("responseCode").equals(404)){
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }else{
            responseEntity = ResponseEntity.ok(responseMap);
        }
        return responseEntity;
    }

    @Override
    @GetMapping(GET_ARTISTS_ENDPOINT)
    public ResponseEntity<Map<String, Object>> getAllArtists(){
        LOGGER.info("============= Get All Artists =============");
        responseMap = artistService.get();
        responseEntity = ResponseEntity.ok(responseMap);
        return responseEntity;
    }

    @Override
    @PostMapping(POST_ARTIST_ENDPOINT)
    public ResponseEntity<Map<String, Object>> postArtist(@RequestBody Artist artist){
        LOGGER.info("============ Post Artist ============");
        responseMap = artistService.post(artist);
        responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        return responseEntity;
    }

    @Override
    @PutMapping(PUT_ARTIST_BY_ID_ENDPOINT)
    public ResponseEntity<Map<String, Object>> putArtist(@PathVariable String artistId, @RequestBody Artist artist){
        LOGGER.info("============ Put Artist ============");
        responseMap = artistService.updateById(artistId, artist);
        if(responseMap.get("responseCode").equals(404)){
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }else{
            responseEntity = ResponseEntity.ok(responseMap);
        }
        return responseEntity;
    }

    @Override
    @DeleteMapping(DELETE_ARTIST_BY_ID_ENDPOINT)
    public ResponseEntity<Map<String, Object>> deleteArtist(@PathVariable String artistId){
        LOGGER.info("=========== Delete Artist ============");
        responseMap = artistService.deleteById(artistId);
        if(responseMap.get("responseCode").equals(404)){
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }else{
            responseEntity = ResponseEntity.ok(responseMap);
        }
        return responseEntity;
    }
}
