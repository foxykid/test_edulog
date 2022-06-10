package com.example.demo.service.impl;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Link;
import com.example.demo.model.SampleDataTest;
import com.example.demo.repository.GPSRepository;
import com.example.demo.service.GPSService;
import com.example.demo.util.FileUtil;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.WayPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GPSServiceImplTest {

    @Autowired
    private GPSService gpsService;

    @Autowired
    private GPSRepository gpsRepository;

    @Test
    public void testParseGPX() throws IOException {
        File file = new File("src/test/java/com/example/demo/sample/sample.gpx");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            MockMultipartFile mockMultipartFile =
                    new MockMultipartFile(
                            "file",
                            file.getName(),
                            FileUtil.GPX_CONTENT_TYPE,
                            inputStream);
            GPX gpx = gpsService.uploadFile(mockMultipartFile);
            Assert.assertEquals("1.1", gpx.getVersion());
            Assert.assertEquals("OruxMaps v.7.1.6 Donate", gpx.getCreator());
            Assert.assertTrue(gpx.getMetadata().isPresent());
            Metadata metaData = gpx.getMetadata().get();
            Assert.assertEquals(
                    "Bardenas Reales: Piskerra y el Paso de los Ciervos",
                    metaData.getName().get());
            Assert.assertEquals(
                    "Este espectacular Parque Natural semidesértico",
                    metaData.getDescription().get().substring(0,46));
            Assert.assertTrue(metaData.getAuthor().isPresent());
            Assert.assertEquals("http://www.oruxmaps.com", metaData.getLinks().get(0).getHref().toString());
            Assert.assertEquals("OruxMaps", metaData.getLinks().get(0).getText().get());
            Assert.assertEquals(ZonedDateTime.parse("2017-10-22T09:41:33Z"), metaData.getTime().get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testSaveGPS() {
        GPS sampleGPS = SampleDataTest.sampleSaveDataGPS();
        GPS save = gpsRepository.save(sampleGPS);
        GPS get = gpsRepository.findOne(save.getId());

        assertEquals(sampleGPS.getCreator(), get.getCreator());
        assertEquals(sampleGPS.getVersion(), get.getVersion());

        com.example.demo.domain.Metadata metadataSave = sampleGPS.getMetadata();
        com.example.demo.domain.Metadata metadataGet = get.getMetadata();
        assertEquals(metadataSave.getName(), metadataGet.getName());
        assertEquals(metadataSave.getDescription(), metadataGet.getDescription());
        assertEquals(metadataSave.getTime(), metadataGet.getTime());

        Link linkSave = metadataSave.getLink();
        Link linkGet = metadataGet.getLink();
        assertEquals(linkSave.getHref(), linkGet.getHref());
        assertEquals(linkSave.getText(), linkGet.getText());

        assertEquals(sampleGPS.getWaypoints().size(), get.getWaypoints().size());
        assertEquals(sampleGPS.getTracks().size(), get.getTracks().size());
        assertEquals(sampleGPS.getTracks().get(0).getTrackSegment().size(),
                get.getTracks().get(0).getTrackSegment().size());
        assertEquals(sampleGPS.getTracks().get(0).getTrackSegment().get(0).getWaypoints().size(),
                get.getTracks().get(0).getTrackSegment().get(0).getWaypoints().size());
    }
}
