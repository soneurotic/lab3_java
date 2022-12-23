package org.fpm.di.example;

import org.fpm.di.Binder;

import java.util.ArrayList;
import java.util.HashMap;
//didn't use
//import java.util.LinkedList;
import java.util.List;

public class DummyBinder implements Binder {
    List<Class<?>> LIST = new ArrayList<>();
    HashMap<Class<?>, Object> IMPLEMENTATION = new HashMap<>();
    HashMap<Class<?>, Object> INSTANCE = new HashMap<>();
    @Override
    public <T> void bind(Class<T> clazz) {
        LIST.add(clazz);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        IMPLEMENTATION.put(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        INSTANCE.put(clazz, instance);
    }
}
