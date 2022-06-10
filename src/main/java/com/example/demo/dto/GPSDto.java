package com.example.demo.dto;

import com.example.demo.domain.GPS;
import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.domain.Waypoint;

import java.sql.Timestamp;
import java.util.List;

public class GPSDto {

    private Long id;

    private String creator;

    private String version;

    private Metadata metadata;

    private List<Waypoint> waypoints;

    private List<Track> tracks;

    private Timestamp createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public GPSDto(GPS gps) {
        this.creator = gps.getCreator();
        this.version = gps.getVersion();
        this.metadata = gps.getMetadata();
        this.waypoints = gps.getWaypoints();
        this.tracks = gps.getTracks();
        this.createdTime = gps.getCreatedTime();
    }
}
