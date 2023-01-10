package com.example.demo.entity.dtos;

import com.example.demo.entity.models.Artist;
import com.example.demo.entity.models.Song;
import com.example.demo.interfaces.repositories.ArtistRepository;
import com.example.demo.interfaces.services.implementations.ArtistServiceImpl;
import com.example.demo.utils.QueryUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
public class SongDTO {

    private String hateoasURL;
    private String songName;

    public SongDTO(Song song){
        this.songName = song.getSongName();
        this.hateoasURL = "http://localhost:8080/v1/songs/" + song.getSongId();
    }
}
