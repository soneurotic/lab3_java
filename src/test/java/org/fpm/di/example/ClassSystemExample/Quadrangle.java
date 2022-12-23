package org.fpm.di.example.ClassSystemExample;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Quadrangle {
    private final Rectangle rectangle;

    @Inject
    public Quadrangle(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
