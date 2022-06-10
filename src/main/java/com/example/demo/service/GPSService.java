package com.example.demo.service;

import com.example.demo.dto.GPSDto;
import io.jenetics.jpx.GPX;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GPSService {

    GPX uploadFile(MultipartFile multipartFile);

    void saveFile(GPX gpx);

    List<GPSDto> getLatestGPSList(Pageable pageRequest);

    GPX getGPSById(Long gpsId);

}
