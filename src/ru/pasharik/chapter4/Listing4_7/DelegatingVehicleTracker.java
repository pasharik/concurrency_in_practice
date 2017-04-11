package ru.pasharik.chapter4.Listing4_7;

import net.jcip.annotations.ThreadSafe;
import ru.pasharik.chapter4.Listing4_6.ImmutablePoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by pasharik on 11/04/17.
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, ImmutablePoint> locations;
    private final Map<String, ImmutablePoint> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, ImmutablePoint> map) {
        locations = new ConcurrentHashMap<>(map);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, ImmutablePoint> getLocations() {
        return unmodifiableMap;
    }

    public Map<String, ImmutablePoint> getLocationsSnapshot() {
        return Collections.unmodifiableMap(new HashMap<String, ImmutablePoint>(locations));
    }

    public ImmutablePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new ImmutablePoint(x, y)) == null)
            throw new IllegalArgumentException("Unexpected id: " + id);
    }
}
