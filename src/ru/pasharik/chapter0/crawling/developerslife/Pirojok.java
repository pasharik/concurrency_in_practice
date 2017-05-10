package ru.pasharik.chapter0.crawling.developerslife;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by pasharik on 08/05/17.
 */
@ThreadSafe
public class Pirojok {
    private final int id;
    private final int rating;
    private final String text;

    public Pirojok(int id, int rating, String text) {
        this.id = id;
        this.rating = rating;
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pirojok && this.id == ((Pirojok) obj).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return id + " - " + rating + "\n" + text;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }
}
