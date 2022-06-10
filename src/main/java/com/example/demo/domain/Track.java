package com.example.demo.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<TrackSegment> trackSegments;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    private GPS gps;

    public GPS getGps() {
        return gps;
    }

    public Track setGps(GPS gps) {
        this.gps = gps;
        return this;
    }

    public List<TrackSegment> getTrackSegment() {
        return trackSegments;
    }

    public void setTrackSegments(List<TrackSegment> trackSegments) {
        this.trackSegments = trackSegments;
        trackSegments.forEach(trackSegment -> trackSegment.setTrack(this));
    }
}
