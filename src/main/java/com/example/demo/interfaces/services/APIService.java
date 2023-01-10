package com.example.demo.interfaces.services;

import java.util.Map;

public interface APIService {
    Map<String, Object> getById(String id);
    Map<String, Object> deleteById(String id);
    Map<String, Object> get();
}
