package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.fpm.di.example.ClassSystemExample.*;

public class MyConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);

        binder.bind(UseA.class);

        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
//        -----------------
        binder.bind(Figure.class);
        binder.bind(PatternPerimeter.class);
        binder.bind(Ellipse.class, Circle.class);
        binder.bind(Circle.class, SomeCircle.class);
        binder.bind(Triangle.class, new Triangle());
        binder.bind(Square.class, new Square());
    }
}
