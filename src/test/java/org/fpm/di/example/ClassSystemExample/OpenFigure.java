package org.fpm.di.example.ClassSystemExample;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OpenFigure {
    private final Segment segment;
    private final Polyline polyline;

    @Inject
    public OpenFigure(Segment segment, Polyline polyline){
        this.segment = segment;
        this.polyline = polyline;
    }

    public Segment getSegment() {
        return segment;
    }

    public Polyline getPolyline() {
        return polyline;
    }
}
