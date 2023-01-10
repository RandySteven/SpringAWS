package com.example.demo.interfaces.services;

import com.example.demo.entity.models.Artist;

import java.util.Map;

public interface ArtistService extends APIService{
    Map<String, Object> post(Artist artist);

    Map<String, Object> updateById(String id, Artist artist);
}
