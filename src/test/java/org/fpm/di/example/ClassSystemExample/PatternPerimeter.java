package org.fpm.di.example.ClassSystemExample;

import javax.inject.Inject;

public class PatternPerimeter {
    private final OpenFigure openFigure;
    private final ClosedFigure closedFigure;

    @Inject
    public PatternPerimeter(OpenFigure AnotherOpenFigure, ClosedFigure AnotherClosedFigure){
        this.openFigure = AnotherOpenFigure;
        this.closedFigure = AnotherClosedFigure;
    }

    public OpenFigure getOpenFigure() {
        return openFigure;
    }

    public ClosedFigure getClosedFigure() {
        return closedFigure;
    }
}
