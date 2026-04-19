package com.eventmgmt.service;

import com.eventmgmt.model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private List<Event> events = new ArrayList<>();

    public EventService() {
        events.add(new Event("E101", "Tech Conference", 10));
        events.add(new Event("E102", "Music Fest", 8));
        events.add(new Event("E103", "Startup Expo", 12));
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public Event getEventById(String id) {
        for (Event e : events) {
            if (e.getEventId().equals(id)) {
                return e;
            }
        }
        return null;
    }
}