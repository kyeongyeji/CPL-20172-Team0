package com.jongpp.navigation;

/**
 * Created by Owner on 2017-11-23.
 */

public class Vertex {

    String id;
    String x, y;

    public Vertex(String x, String y) {
        this.x = x;
        this.y = y;

        this.id = x+y;
    }

    public Vertex() {

    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}