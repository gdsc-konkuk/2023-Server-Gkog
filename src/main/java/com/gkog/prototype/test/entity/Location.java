package com.gkog.prototype.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.geo.Point;

@Entity
@Getter
public class Location {

    @Id @GeneratedValue
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String roadAddress;

    @Column(nullable = false)
    private float longitude;

    @Column(nullable = false)
    private float latitude;

}
