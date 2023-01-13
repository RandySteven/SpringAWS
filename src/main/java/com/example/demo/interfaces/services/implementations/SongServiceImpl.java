package com.example.demo.interfaces.services.implementations;

import com.example.demo.entity.dtos.SongDTO;
import com.example.demo.entity.models.Song;
import com.example.demo.interfaces.repositories.SongRepository;
import com.example.demo.interfaces.services.SongService;
import com.example.demo.utils.IdGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    private Map<String, Object> responseMap;

    List<Song> songs;

    @Autowired
    private IdGeneratorUtil idGeneratorUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(SongServiceImpl.class);

    @Override
    public Map<String, Object> getById(String id) {
        responseMap = new HashMap<>();
        Song song = songRepository.findBySongId(id);
        if(song.getDeletedAt() != null || song == null){
            responseMap.put("responseCode", 404);
            responseMap.put("responseMessage", "Song not found");
        }else{
            responseMap.put("responseCode", 200);
            responseMap.put("responseMessage", "Get song");
            responseMap.put("song", song);
        }
        return responseMap;
    }

    @Override
    public Map<String, Object> deleteById(String id) {
        responseMap = new HashMap<>();
        Song getSong = songRepository.findBySongId(id);
        if(getSong.getDeletedAt() != null || getSong == null){
            responseMap.put("responseCode", 404);
            responseMap.put("responseMessage", "Song not found");
        }else{
            getSong.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            Song updatedSong = songRepository.save(getSong);
            responseMap.put("responseCode", 200);
            responseMap.put("responseMessage", "Delete song success");
            responseMap.put("song", updatedSong);
        }
        return null;
    }

    @Override
    public Map<String, Object> get() {
        responseMap = new HashMap<>();
        songs = songRepository.findAll();

        responseMap.put("responseCode", 200);
        if(songs.isEmpty()){
            responseMap.put("responseMessage", "Song still empty");
        }else{
            List<SongDTO> songDTOS = new ArrayList<>();
            songs.forEach(song -> {
//                if(song.getDeletedAt() == null)
                    songDTOS.add(new SongDTO(song));
            });
            responseMap.put("responseMessage", "Get all songs");
            responseMap.put("songs", songDTOS);
        }
        return responseMap;
    }

    @Override
    public Map<String, Object> post(Song song) {
        responseMap = new HashMap<>();
        song.setSongId(idGeneratorUtil.generateSongId());
        song.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        song.setUpdatedAt(null);
        song.setDeletedAt(null);

        LOGGER.info("=========== song request : " + song);

        Song postSong = songRepository.save(song);

        responseMap.put("responseCode", 201);
        responseMap.put("responseMessage", "Add song success");
        responseMap.put("song", postSong);
        return responseMap;
    }

    @Override
    public Map<String, Object> updateById(String id, Song song) {
        responseMap = new HashMap<>();
        Song getSong = songRepository.findBySongId(id);
        if(getSong.getDeletedAt() != null || getSong == null){
            responseMap.put("responseCode", 404);
            responseMap.put("responseMessage", "Song not found");
        }else{
            getSong.setSongName(
                    song.getSongName() != null ? song.getSongName() : getSong.getSongName()
            );
            getSong.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            Song updatedSong = songRepository.save(getSong);
            responseMap.put("responseCode", 200);
            responseMap.put("responseMessage", "Update song success");
            responseMap.put("song", updatedSong);
        }
        return responseMap;
    }
}
