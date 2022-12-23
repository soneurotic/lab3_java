package org.fpm.di.example.ClassSystemExample;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClosedFigure {
    private final Polygon polygon;
    private final Ellipse ellipse;

    @Inject
    public ClosedFigure(Polygon polygon, Ellipse ellipse){
        this.polygon = polygon;
        this.ellipse = ellipse;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Ellipse getEllipse() {
        return ellipse;
    }
}
