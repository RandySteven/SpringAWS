package com.example.demo.interfaces.repositories;

import com.example.demo.entity.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM songs s WHERE s.songId = ?1")
    Song findBySongId(String songId);
}
