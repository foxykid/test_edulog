package com.example.demo.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Waypoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private double latitude;

    private double longitude;

    private String name;

    private String symbol;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    private GPS gps;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    private TrackSegment trackSegments;

    public GPS getGps() {
        return gps;
    }

    public void setGps(GPS gps) {
        this.gps = gps;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public TrackSegment getTrackSegments() {
        return trackSegments;
    }

    public void setTrackSegments(TrackSegment trackSegments) {
        this.trackSegments = trackSegments;
    }
}
