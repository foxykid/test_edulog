package com.example.demo.repository;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Link;
import com.example.demo.domain.Metadata;
import com.example.demo.model.SampleDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GPSRepositoryTest {

    @Mock
    private GPSRepository gpsRepository;

    @Test
    @Transactional
    public void testGetGPSById() {
        GPS sampleGPS = SampleDataTest.sampleSaveDataGPS();
        GPS save = gpsRepository.save(sampleGPS);
        GPS get = gpsRepository.findOne(save.getId());

        assertEquals(sampleGPS.getCreator(), get.getCreator());
        assertEquals(sampleGPS.getVersion(), get.getVersion());

        Metadata metadataSave = sampleGPS.getMetadata();
        Metadata metadataGet = get.getMetadata();
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

    @Test
    @Transactional
    public void testGetAll() {
        GPS sampleGPS = SampleDataTest.sampleSaveDataGPS();
        gpsRepository.save(sampleGPS);
        GPS get = gpsRepository.findAll().get(0);

        assertEquals(sampleGPS.getCreator(), get.getCreator());
        assertEquals(sampleGPS.getVersion(), get.getVersion());

        Metadata metadataSave = sampleGPS.getMetadata();
        Metadata metadataGet = get.getMetadata();
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
