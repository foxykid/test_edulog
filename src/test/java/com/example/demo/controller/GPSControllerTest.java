package com.example.demo.controller;

import com.example.demo.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GPSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUploadFile() throws Exception {
        File sample = new File("src/test/java/com/example/demo/sample/sample.gpx");
        try(FileInputStream inputStream = new FileInputStream(sample)) {
            MockMultipartFile mockMultipartFile = new MockMultipartFile("file",
                    sample.getName(),
                    FileUtil.GPX_CONTENT_TYPE,
                    inputStream);
            mockMvc.perform(MockMvcRequestBuilders
                    .fileUpload("/gps/uploadFile")
                    .file(mockMultipartFile))
                    .andExpect(status().is2xxSuccessful());
        }
    }

}
