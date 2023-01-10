package com.example.demo.interfaces.services;

import com.example.demo.entity.models.Song;

import java.util.Map;

public interface SongService extends APIService{

    Map<String, Object> post(Song song);
    Map<String, Object> updateById(String id, Song song);

}
