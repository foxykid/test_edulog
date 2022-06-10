package com.example.demo.mapper;

import com.example.demo.domain.GPS;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Link;
import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;

import java.util.Optional;
import java.util.stream.Collectors;

public class GPSToGPXMapper {

    public static GPX gpsToGPX (GPS gps) {
        return GPX.builder().creator(gps.getCreator())
                            .metadata(metadataGPSToGPX(gps.getMetadata()))
                            .wayPoints(gps.getWaypoints().stream().map(GPSToGPXMapper::wayPointGPSToGPX).collect(Collectors.toList()))
                            .tracks(gps.getTracks().stream().map(GPSToGPXMapper::trackGPSToGPX).collect(Collectors.toList()))
                            .build();
    }

    public static Metadata metadataGPSToGPX (com.example.demo.domain.Metadata metadataGPS) {
        Metadata.Builder builder = Metadata.builder();
        if (metadataGPS.getAuthor() != null) {
            builder.author(metadataGPS.getAuthor());
        }
        return builder.addLink(linkGPSToGPX(metadataGPS.getLink()))
                .desc(metadataGPS.getDescription())
                .name(metadataGPS.getName())
                .build();
    }

    public static Link linkGPSToGPX (com.example.demo.domain.Link linkGPS) {
        return Link.of(linkGPS.getHref(), linkGPS.getText(), null);
    }

    public static WayPoint wayPointGPSToGPX (com.example.demo.domain.Waypoint waypointGPS) {
        return WayPoint.builder()
                .lat(waypointGPS.getLatitude())
                .lon(waypointGPS.getLongitude())
                .name(waypointGPS.getName())
                .sym(waypointGPS.getSymbol())
                .build();
    }

    public static Track trackGPSToGPX (com.example.demo.domain.Track trackGPS) {
        return Track.builder()
                .segments(trackGPS.getTrackSegment().stream().map(GPSToGPXMapper::trackSegmentGPSToGPX).collect(Collectors.toList()))
                .build();
    }

    public static TrackSegment trackSegmentGPSToGPX (com.example.demo.domain.TrackSegment trackSegmentGPS) {
        return TrackSegment.builder()
                .points(trackSegmentGPS.getWaypoints().stream().map(GPSToGPXMapper::wayPointGPSToGPX).collect(Collectors.toList()))
                .build();
    }

}
