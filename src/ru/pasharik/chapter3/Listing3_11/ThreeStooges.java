package ru.pasharik.chapter3.Listing3_11;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pasharik on 03/04/17.
 */
@Immutable
public class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
