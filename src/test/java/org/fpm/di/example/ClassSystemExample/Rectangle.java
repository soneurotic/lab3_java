package org.fpm.di.example.ClassSystemExample;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Rectangle {
    private final Square square;

    @Inject
    public Rectangle(Square NewSquare){
        this.square = NewSquare;
    }

    public Square getSquare() {
        return square;
    }
}
