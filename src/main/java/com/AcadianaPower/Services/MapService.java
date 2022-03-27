package com.AcadianaPower.Services;

import com.AcadianaPower.Models.MapModel;
import com.AcadianaPower.Repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MapService {

    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository){
        this.mapRepository = mapRepository;
    }

    public List<MapModel> tractByZip(Integer zipCode){
        return mapRepository.tractByZip(zipCode).orElseGet(Arrays::asList);
    }

    public List<MapModel> allMapInfo(){
        return mapRepository.findAll();
    }
}
