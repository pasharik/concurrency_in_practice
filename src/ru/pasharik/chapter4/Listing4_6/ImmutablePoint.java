package ru.pasharik.chapter4.Listing4_6;

import net.jcip.annotations.Immutable;

/**
 * Created by pasharik on 10/04/17.
 */
@Immutable
public class ImmutablePoint {
    public final int x, y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
