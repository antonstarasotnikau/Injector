package by.incubator.infrastructur.impl;

import by.incubator.infrastructur.Provider;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ProviderImpl<T> implements Provider<T> {
    private final Class<T> impl;
    private final Class<?>[] paramTypes;
    private final Object[] listInitArgs;

    public ProviderImpl(Class<T> impl, Class<?>[] paramTypes, Object[] listInitArgs) {
        this.impl = impl;
        this.paramTypes = paramTypes;
        this.listInitArgs = listInitArgs;
    }

    public ProviderImpl(Class<T> impl) {
        this.impl = impl;
        this.paramTypes = null;
        this.listInitArgs = null;
    }

    @Override
    public T getInstance() {
        T obj = null;
        try {
            obj = impl.getDeclaredConstructor(paramTypes).newInstance(listInitArgs);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
