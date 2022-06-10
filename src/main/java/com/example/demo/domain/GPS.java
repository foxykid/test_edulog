package com.example.demo.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class GPS {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String creator;

    private String version;

    @OneToOne(cascade = CascadeType.ALL)
    private Metadata metadata;

    @OneToMany(mappedBy = "gps", cascade = CascadeType.ALL)
    private List<Waypoint> waypoints;

    @OneToMany(mappedBy = "gps", cascade = CascadeType.ALL)
    private List<Track> tracks;

    @CreationTimestamp
    private Timestamp createdTime;

    public Long getId() {
        return id;
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
        waypoints.forEach(waypoint -> waypoint.setGps(this));
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
        tracks.forEach(track -> track.setGps(this));
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

}
