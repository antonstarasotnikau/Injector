package by.incubator.infrastructur.impl;

import by.incubator.infrastructur.Provider;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ProviderImpl<T> implements Provider<T> {
    private final Class<T> impl;

    public ProviderImpl(Class<T> impl) {
        this.impl = impl;
    }

    @Override
    public T getInstance() {
        T obj = null;
        try {
            obj = impl.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
