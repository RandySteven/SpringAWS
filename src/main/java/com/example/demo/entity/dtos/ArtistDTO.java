package com.example.demo.entity.dtos;

import com.example.demo.entity.models.Artist;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArtistDTO {

    private String urlHateoas;
    private String artistName;
    private String artistDescription;

    public ArtistDTO(Artist artist){
        this.urlHateoas = "http://localhost:8080/v1/artists/" + artist.getArtistId();
        this.artistName = artist.getArtistName();
        this.artistDescription = artist.getArtistDescription();
    }
}
