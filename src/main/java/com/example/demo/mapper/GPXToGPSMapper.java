package com.example.demo.mapper;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Link;
import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.domain.TrackSegment;
import com.example.demo.domain.Waypoint;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Person;
import io.jenetics.jpx.WayPoint;

import java.sql.Timestamp;
import java.util.stream.Collectors;

public class GPXToGPSMapper {

    public static GPS gpxToGPS (GPX gpx) {
        GPS gps = new GPS();
        gps.setCreator(gpx.getCreator());
        gps.setVersion(gpx.getVersion());
        gps.setTracks(gpx.getTracks().stream().map(GPXToGPSMapper::trackGPSToGPX).collect(Collectors.toList()));
        gps.setWaypoints(gpx.getWayPoints().stream().map(GPXToGPSMapper::wayPointGPXToGPS).collect(Collectors.toList()));
        gpx.getMetadata().ifPresent(metadata -> gps.setMetadata(metadataGPXToGPS(metadata)));
        return gps;
    }

    public static Metadata metadataGPXToGPS (io.jenetics.jpx.Metadata metadataGPX) {
        Metadata metadataGPS = new Metadata();
        metadataGPX.getAuthor().flatMap(Person::getName).ifPresent(metadataGPS::setAuthor);
        metadataGPX.getDescription().ifPresent(metadataGPS::setDescription);
        metadataGPS.setLink(linkGPXToGPS(metadataGPX.getLinks().get(0)));
        metadataGPX.getTime().ifPresent(x -> metadataGPS.setTime(Timestamp.from(x.toInstant())));
        return metadataGPS;
    }

    public static Link linkGPXToGPS (io.jenetics.jpx.Link linkGPX) {
        Link linkGPS = new Link();
        linkGPS.setHref(linkGPX.getHref().toString());
        linkGPS.setText(linkGPX.getText().toString());
        return linkGPS;
    }

    public static Waypoint wayPointGPXToGPS (WayPoint waypointGPX) {
        Waypoint waypoint = new Waypoint();
        waypoint.setLatitude(waypointGPX.getLatitude().doubleValue());
        waypoint.setLongitude(waypointGPX.getLongitude().doubleValue());
        waypointGPX.getName().ifPresent(waypoint::setName);
        waypointGPX.getSymbol().ifPresent(waypoint::setSymbol);
        return waypoint;
    }

    public static Track trackGPSToGPX (io.jenetics.jpx.Track trackGPX) {
        Track track = new Track();
        track.setTrackSegments(trackGPX.getSegments().stream().map(GPXToGPSMapper::trackSegmentGPXToGPS).collect(Collectors.toList()));
        return track;
    }

    public static TrackSegment trackSegmentGPXToGPS (io.jenetics.jpx.TrackSegment trackSegmentGPX) {
        TrackSegment trackSegment = new TrackSegment();
        trackSegment.setWaypoints(trackSegmentGPX.getPoints().stream().map(GPXToGPSMapper::wayPointGPXToGPS).collect(Collectors.toList()));
        return trackSegment;
    }
}
