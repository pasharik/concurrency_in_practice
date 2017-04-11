package ru.pasharik.chapter4.Listing4_12;

import net.jcip.annotations.ThreadSafe;
import ru.pasharik.chapter4.Listing4_11.SafePoint;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pasharik on 11/04/17.
 */
@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> map) {
        this.locations = new ConcurrentHashMap<>(map);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id))
            throw new IllegalArgumentException("Invalid id: " + id);
        locations.get(id).set(x, y);
    }
}
