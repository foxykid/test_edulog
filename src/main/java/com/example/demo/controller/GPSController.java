package com.example.demo.controller;

import com.example.demo.enums.ErrorType;
import com.example.demo.exception.FileFormatException;
import com.example.demo.model.ResponseModel;
import com.example.demo.service.GPSService;
import com.example.demo.util.FileUtil;
import io.jenetics.jpx.GPX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/gps")
public class GPSController {

    @Autowired
    private GPSService gpsService;

    @PostMapping("/uploadFile")
    public ResponseEntity<ResponseModel> uploadFile(@RequestParam("file")MultipartFile file) {
        if (!FileUtil.validateFormatUploadFile(file)) {
            throw new FileFormatException(ErrorType.INVALID_FORMAT);
        }
        GPX gpx = gpsService.uploadFile(file);
        gpsService.saveFile(gpx);
        return ResponseEntity.ok(ResponseModel.createdInstance());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseModel> getLatestGPSList(@PageableDefault Pageable pageRequest) {
        return ResponseEntity.ok(ResponseModel.okInstance().withData(gpsService.getLatestGPSList(pageRequest)));
    }

    @GetMapping("/detail/{gpsId}")
    public ResponseEntity<ResponseModel> getGPSById(@NotNull @PathVariable Long gpsId) {
        return ResponseEntity.ok(ResponseModel.okInstance().withData(gpsService.getGPSById(gpsId)));
    }

}
