package com.example.demo.interfaces.services.implementations;

import com.example.demo.entity.dtos.ArtistDTO;
import com.example.demo.entity.models.Artist;
import com.example.demo.interfaces.repositories.ArtistRepository;
import com.example.demo.interfaces.services.ArtistService;
import com.example.demo.utils.IdGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private IdGeneratorUtil idGeneratorUtil;

    private Artist artist;

    List<Artist> artists;

    List<ArtistDTO> artistDTOS;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private Map<String, Object> responseMap;

    @Override
    public Map<String, Object> getById(String id) {
        responseMap = new HashMap<>();
        artist = artistRepository.findByArtistId(id);
        LOGGER.info("========= Artist : " + artist);
        if(artist != null){
            responseMap.put("responseCode", 200);
            responseMap.put("success", true);
            responseMap.put("responseMessage", "Get Artist");
            responseMap.put("artist", artist);
        }else{
            responseMap.put("responseCode", 404);
            responseMap.put("success", false);
            responseMap.put("responseMessage", "Artist Not Found");
        }
        LOGGER.info("========= Get Artist Response : " + responseMap);
        return responseMap;
    }

    @Override
    public Map<String, Object> deleteById(String id) {
        responseMap = new HashMap<>();
        Artist getArtist = artistRepository.findByArtistId(id);
        LOGGER.info("========= Request Artist : " + getArtist);
        if(getArtist != null){
            getArtist.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            Artist deleteArtist = artistRepository.save(getArtist);
            responseMap.put("responseCode", 200);
            responseMap.put("responseMessage", "Get Artist");
            responseMap.put("artist", deleteArtist);
        }else{
            responseMap.put("responseCode", 404);
            responseMap.put("responseMessage", "Artist Not Found");
        }
        return responseMap;
    }

    @Override
    public Map<String, Object> get() {
        responseMap = new HashMap<>();
        artistDTOS = new ArrayList<>();
        artists = artistRepository.findAll();
        LOGGER.info("========= List of artists : " + artists);
        artists.forEach(artist -> {
            if(artist.getDeletedAt() == null)
                artistDTOS.add(new ArtistDTO(artist));
        });
        responseMap.put("responseCode", 200);
        if(artists.isEmpty()){
            responseMap.put("responseMessage", "Artists still empty");
        }else{
            responseMap.put("responseMessage", "Get all artists");
        }
        responseMap.put("artists", artistDTOS.toArray());
        return responseMap;
    }

    @Override
    public Map<String, Object> post(Artist artist) {
        responseMap = new HashMap<>();
        artists = artistRepository.findAll();
        long currId = artists.size() + 1;
        artist.setId(currId);
        artist.setArtistId(idGeneratorUtil.generateArtistId());
        artist.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        artist.setUpdatedAt(null);
        artist.setDeletedAt(null);
        LOGGER.info("======= Artist Request Body : " + artist);
        Artist postArtist = artistRepository.save(artist);
        responseMap.put("responseCode", 201);
        responseMap.put("responseMessage", "Created artist success");
        responseMap.put("artist", postArtist);
        return responseMap;
    }

    @Override
    public Map<String, Object> updateById(String id, Artist artist) {
        responseMap = new HashMap<>();
        Artist getArtist = artistRepository.findByArtistId(id);
        LOGGER.info("========= Request Artist : " + getArtist);
        if(getArtist != null){
            getArtist.setArtistName(
                    artist.getArtistName() == null ? getArtist.getArtistName() : artist.getArtistName()
            );
            getArtist.setArtistDescription(
                    artist.getArtistDescription() == null ? getArtist.getArtistDescription() : artist.getArtistDescription()
            );
            getArtist.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            Artist newArtist = artistRepository.save(getArtist);
            responseMap.put("responseCode", 200);
            responseMap.put("responseMessage", "Get Artist");
            responseMap.put("old_artist", getArtist);
            responseMap.put("new_artist", newArtist);
        }else{
            responseMap.put("responseCode", 404);
            responseMap.put("responseMessage", "Artist Not Found");
        }
        return responseMap;
    }
}
