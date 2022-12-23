package org.fpm.di.example.ClassSystemExample;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Polygon {
    private final Triangle triangle;
    private final Quadrangle quadrangle;

    @Inject
    public Polygon(Triangle triangle, Quadrangle quadrangle){
        this.triangle = triangle;
        this.quadrangle = quadrangle;
    }

    public Triangle getTriangle() {
        return triangle;
    }

    public Quadrangle getQuadrangle() {
        return quadrangle;
    }
}
