package com.example.demo.entity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Table(name = " artists")
@Entity(name = "artists")
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @Column(name = "ID", nullable = true)
    private Long id;

    @Column(name = "artist_id")
    private String artistId;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "artist_description")
    private String artistDescription;

    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(name="updated_at")
    private Timestamp updatedAt;
    @Column(name="deleted_at")
    private Timestamp deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArtistId(String artistId){
        this.artistId = artistId;
    }

    public String getArtistId(){
        return artistId;
    }

    public void setArtistName(String artistName){
        this.artistName = artistName;
    }

    public String getArtistName(){
        return artistName;
    }

    public void setArtistDescription(String artistDescription){
        this.artistDescription = artistDescription;
    }

    public String getArtistDescription(){
        return artistDescription;
    }

    public void setCreatedAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt(){
        return createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt){
        this.updatedAt = updatedAt;
    }

    public Timestamp getUpdatedAt(){
        return updatedAt;
    }

    public void setDeletedAt(Timestamp deletedAt){
        this.deletedAt = deletedAt;
    }

    public Timestamp getDeletedAt(){
        return deletedAt;
    }


}
