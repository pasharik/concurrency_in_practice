package ru.pasharik.chapter4.Listing4_4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.pasharik.chapter4.Listing4_5.MutablePoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pasharik on 10/04/17.
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint p = locations.get(id);
        return p == null ? null : new MutablePoint(p);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint p = locations.get(id);
        if (p == null) throw new IllegalArgumentException("Wrong id: " + id);
        p.x = x;
        p.y = y;
    }

    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (Map.Entry<String, MutablePoint> e : m.entrySet()) {
            result.put(e.getKey(), new MutablePoint(e.getValue()));
        }
        return Collections.unmodifiableMap(result);
    }
}
