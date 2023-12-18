package com.gkog.prototype.test.repository;

import com.gkog.prototype.test.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
