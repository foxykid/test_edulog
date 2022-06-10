package com.example.demo.service.impl;

import com.example.demo.domain.GPS;
import com.example.demo.dto.GPSDto;
import com.example.demo.enums.ErrorType;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.GPSToGPXMapper;
import com.example.demo.mapper.GPXToGPSMapper;
import com.example.demo.repository.GPSRepository;
import com.example.demo.service.GPSService;
import io.jenetics.jpx.GPX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GPSServiceImpl implements GPSService {

    private static final Logger log = LoggerFactory.getLogger(GPSServiceImpl.class);

    @Autowired
    GPSRepository gpsRepository;

    @Override
    public GPX uploadFile(MultipartFile multipartFile) {
        GPX gpx;
        try(InputStream inputStream = multipartFile.getInputStream()) {
            gpx = GPX.read(inputStream);
            return gpx;
        } catch (IOException e) {
            log.error("=========================Parse failed!!!=========================");
            throw new ServiceException(ErrorType.INVALID_FORMAT);
        }
    }

    @Override
    public void saveFile(GPX gpx) {
        GPS gps = GPXToGPSMapper.gpxToGPS(gpx);
        gpsRepository.save(gps);
    }

    @Override
    public List<GPSDto> getLatestGPSList(Pageable pageRequest) {
        return gpsRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(GPSDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public GPX getGPSById(Long gpsId) {
        GPS gps = gpsRepository.findOne(gpsId);
        if (gps != null) {
            return GPSToGPXMapper.gpsToGPX(gps);
        } else {
            throw new DataNotFoundException(ErrorType.DATA_NOT_FOUND);
        }
    }
}
