package ru.pasharik.chapter4.Listing4_5;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created by pasharik on 10/04/17.
 */
@NotThreadSafe
public class MutablePoint {
    public int x;
    public int y;

    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
