package org.fpm.di.example.ClassSystemExample;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Figure {
    private final OpenFigure openFigure;
    private final ClosedFigure closedFigure;

    @Inject
    public Figure(OpenFigure openFigure, ClosedFigure closedFigure){
        this.openFigure = openFigure;
        this.closedFigure = closedFigure;
    }

    public OpenFigure getOpenFigure() {
        return openFigure;
    }

    public ClosedFigure getClosedFigure() {
        return closedFigure;
    }
}
