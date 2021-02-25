package com.foo.cte.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tnaw on 2/9/17.
 */
public class BaseService {

    private Map<String, Object> map;
    private final ObjectMapper mapper = new ObjectMapper();

    public BaseService() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

//    public Map<String, Object> getMap() {
//        if (map == null) {
//            setMap(new LinkedHashMap<>());
//        }
//        return map;
//    }

    public Map<String, Object> getMap() {
        return new LinkedHashMap<>();
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }


    public ObjectMapper getMapper() {
        return mapper;
    }
}
