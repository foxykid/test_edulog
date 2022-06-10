package com.example.demo.model;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Link;
import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.domain.TrackSegment;
import com.example.demo.domain.Waypoint;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SampleDataTest {

    public static GPS sampleSaveDataGPS() {
        GPS sampleSaveData = new GPS();

        sampleSaveData.setCreator("OruxMaps v.7.1.6 Donate");
        sampleSaveData.setVersion("1.1");
        sampleSaveData.setWaypoints(sampleSaveDataWaypoint());
        sampleSaveData.setMetadata(sampleSaveDataMetadata());
        sampleSaveData.setTracks(sampleSaveDataTrack());

        return sampleSaveData;
    }

    public static List<Waypoint> sampleSaveDataWaypoint() {
        List<Waypoint> sampleSaveData = new ArrayList<>();

        Waypoint waypoint1 = new Waypoint();
        waypoint1.setLatitude(42.2205377);
        waypoint1.setLongitude(-1.4564538);
        waypoint1.setName("Sorteamos por arriba");
        waypoint1.setSymbol("/static/wpt/Waypoint");

        Waypoint waypoint2 = new Waypoint();
        waypoint2.setLatitude(42.2208346);
        waypoint2.setLongitude(-1.4544232);
        waypoint2.setName("Senda");
        waypoint2.setSymbol("/static/wpt/Waypoint");

        sampleSaveData.add(waypoint1);
        sampleSaveData.add(waypoint2);

        return sampleSaveData;
    }

    public static Metadata sampleSaveDataMetadata() {
        Metadata sampleSaveData = new Metadata();

        sampleSaveData.setName("Bardenas Reales: Piskerra y el Paso de los Ciervos");
        sampleSaveData.setDescription("Este espectacular Parque Natural");
        sampleSaveData.setLink(sampleSaveDataLink());
        sampleSaveData.setTime(Timestamp.from(Instant.parse("2017-10-22T09:41:33Z")));

        return sampleSaveData;
    }

    public static Link sampleSaveDataLink() {
        Link sampleSaveData = new Link();

        sampleSaveData.setText("OruxMaps");
        sampleSaveData.setHref("http://www.oruxmaps.com");

        return sampleSaveData;
    }

    public static List<Track> sampleSaveDataTrack() {
        List<Waypoint> waypoints = sampleSaveDataWaypoint();

        TrackSegment trackSegment = new TrackSegment();
        trackSegment.setWaypoints(waypoints);

        List<TrackSegment> trackSegments = new ArrayList<>();
        trackSegments.add(trackSegment);

        Track track = new Track();
        track.setTrackSegments(trackSegments);

        List<Track> tracks = new ArrayList<>();
        tracks.add(track);

        return tracks;
    }

}
