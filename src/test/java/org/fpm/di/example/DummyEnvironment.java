package org.fpm.di.example;

import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.Environment;

public class DummyEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) {
//      оскільки у нас є визначена схема зв'язування, написана y DummyBinder,і написана
//      на її основі конфігурація у MyConfiguration, щоб зв'язати конфігурацію з контейнером,
//      ми у поточному класі DummyEnvironment збираємо нашу конфігурацію на основі всього
//      Binder'a і передаємо її у DummyContainer

        DummyBinder BindedDependencies = new DummyBinder();
        configuration.configure(BindedDependencies);
        return new DummyContainer(BindedDependencies);
    }
}