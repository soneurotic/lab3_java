package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.fpm.di.example.ClassSystemExample.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
//        так, як клас UseA містить інжект конструктора, що присвоює полю
//        dependency класу А якийсь клас А, а клас А в свою чергу імплементує клас В
//        що зазначено у відповідному бінді конфігурації, то замість класу А
//        поле dependency містить імплементацію цього класу (клас В), і відповідно
//        виклик геттера повертає нам клас В
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }
//    --------------------------------------------------------------------------------------------
    @Test
    public void SingletoneInjection(){
        final Figure ExampleFigure = container.getComponent(Figure.class);
        final Triangle ExampleTriangle = container.getComponent(Triangle.class);
        final PatternPerimeter Pattern1 = container.getComponent(PatternPerimeter.class);
        final PatternPerimeter Pattern2 = container.getComponent(PatternPerimeter.class);

        assertSame(ExampleFigure, container.getComponent(Figure.class));
        assertSame(ExampleTriangle, container.getComponent(Triangle.class));
        assertSame(container.getComponent(ClosedFigure.class), container.getComponent(ClosedFigure.class));
        assertSame(Pattern1.getOpenFigure(), Pattern2.getOpenFigure());
        assertSame(Pattern1.getOpenFigure(), container.getComponent(OpenFigure.class));
    }

    @Test
    public void PrototypeInjection(){
        final SomeCircle AnyNewCircle = container.getComponent(SomeCircle.class);
        final PatternPerimeter NewPerimeter = container.getComponent(PatternPerimeter.class);

        assertNotSame(container.getComponent(SomeCircle.class), AnyNewCircle);
        assertNotSame(container.getComponent(SomeCircle.class), container.getComponent(SomeCircle.class));
        assertNotSame(container.getComponent(Segment.class), container.getComponent(Segment.class));
        assertNotSame(NewPerimeter, container.getComponent(PatternPerimeter.class));

    }
    @Test
    public void BuildGraphInjection(){
        final Polygon ExamplePolygon = container.getComponent(Polygon.class);
        final Square ExampleSquare = container.getComponent(Square.class);
        final Figure ExampleFigure = container.getComponent(Figure.class);

        assertSame(ExamplePolygon.getQuadrangle().getRectangle().getSquare(), ExampleSquare);
        assertSame(ExamplePolygon.getTriangle(), container.getComponent(Triangle.class));
        assertNotSame(ExampleFigure.getOpenFigure().getSegment(), container.getComponent(Segment.class));
        assertNotSame(ExampleFigure.getOpenFigure().getPolyline(), container.getComponent(Polyline.class));
    }

    @Test
    public void BuildDI(){
        final SomeCircle NewExampleOfSomeCircle = container.getComponent(SomeCircle.class);
        final Circle ExampleCircle = container.getComponent(Circle.class);
        final Ellipse ExampleEllipse = container.getComponent(Ellipse.class);
        final ClosedFigure ExampleClosedFigure = container.getComponent(ClosedFigure.class);

        assertNotSame(container.getComponent(SomeCircle.class), NewExampleOfSomeCircle);
        assertNotSame(ExampleCircle, NewExampleOfSomeCircle);
        assertNotSame(ExampleEllipse, NewExampleOfSomeCircle);
        assertNotSame(ExampleEllipse, ExampleCircle);
        assertNotSame(ExampleClosedFigure.getEllipse(), ExampleEllipse);
        assertNotSame(ExampleClosedFigure.getEllipse(), ExampleCircle);
        assertNotSame(ExampleClosedFigure.getEllipse(), container.getComponent(SomeCircle.class));
        assertNotSame(ExampleClosedFigure.getEllipse(), NewExampleOfSomeCircle);
    }

    @Test
    public void WithMultipleConstructors(){
        Figure ExampleFigure = container.getComponent(Figure.class);
        OpenFigure ExampleOpenFigure = container.getComponent(OpenFigure.class);
        ClosedFigure ExampleClosedFigure = container.getComponent(ClosedFigure.class);
        Polygon ExamplePolygon = container.getComponent(Polygon.class);
        Polyline ExamplePolyline = container.getComponent(Polyline.class);

        assertSame(ExampleFigure.getOpenFigure(), container.getComponent(OpenFigure.class));
        assertSame(ExampleFigure.getClosedFigure(), container.getComponent(ClosedFigure.class));
        assertNotSame(ExampleOpenFigure.getSegment(), container.getComponent(Segment.class));
        assertNotSame(ExampleOpenFigure.getPolyline(), ExamplePolyline);
        assertSame(ExampleClosedFigure.getPolygon(), container.getComponent(Polygon.class));
        assertSame(ExamplePolygon.getTriangle(), container.getComponent(Triangle.class));
        assertSame(ExamplePolygon.getQuadrangle(), container.getComponent(Quadrangle.class));
    }
}