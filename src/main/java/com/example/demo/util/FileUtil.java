package com.example.demo.util;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static final String GPX_CONTENT_TYPE = "application/gpx+xml";

    public static boolean validateFormatUploadFile(MultipartFile file) {
        return !file.isEmpty()
                && GPX_CONTENT_TYPE.equals(file.getContentType());
    }

}
