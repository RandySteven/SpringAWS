package com.example.demo.interfaces.repositories;

import com.example.demo.entity.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT a FROM artists a WHERE a.artistId = ?1")
    Artist findByArtistId(String id);
}
