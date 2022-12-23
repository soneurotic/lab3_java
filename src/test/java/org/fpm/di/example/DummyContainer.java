package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.*;

public class DummyContainer implements Container {
    private final DummyBinder binder;
    public DummyContainer(DummyBinder binder){
        this.binder = binder;
    }
    @Override
    public <T> T getComponent(Class<T> clazz) {
        if (binder.INSTANCE.containsKey(clazz)) {
            return (T) binder.INSTANCE.get(clazz);
        }
        if (binder.IMPLEMENTATION.containsKey(clazz)) {
            return getComponent((Class <T>) binder.IMPLEMENTATION.get(clazz));
        }
        try {
            T VariableExample = null;
//            цикл, що проходиться по всім конструкторам класу, поміченими анотацією @Inject,
//            і створює інстанси кожного переданого параметру(класу) на вхід до конструктора
            for(Constructor<?> constructor: clazz.getConstructors()){
                if(constructor.isAnnotationPresent(Inject.class)){
                    Object[] parameters = new Object[constructor.getParameterCount()];
                    for(int i = 0; i < constructor.getParameterCount(); i++){
                        parameters[i] = getComponent(constructor.getParameters()[i].getType());
                    }
                    VariableExample = (T) constructor.newInstance(parameters);
                }
            }
            if (VariableExample == null) {
                VariableExample = clazz.newInstance();
            }
            if(clazz.isAnnotationPresent(Singleton.class)){
                binder.bind(clazz, VariableExample);
            }
            return VariableExample;
        } catch(IllegalAccessException|InstantiationException|InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }
}