package com.gkog.prototype.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Event {

    @Id @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(name = "start_info", nullable = false)
    private LocalDateTime startInfo;

    @Column(name = "end_info", nullable = false)
    private LocalDateTime endInfo;

    @Column(length = 10)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String detail;

}
