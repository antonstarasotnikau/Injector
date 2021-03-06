package by.incubator.infrastructure.impl;

import by.incubator.infrastructure.Provider;
import by.incubator.infrastructure.annotation.Singleton;

import java.lang.reflect.InvocationTargetException;

public class ProviderImpl<T> implements Provider<T> {
    private final Class<T> impl;
    private final Class<?>[] paramTypes;
    private final Object[] listInitArgs;
    private final T singletonObject;

    public ProviderImpl(Class<T> impl, Class<?>[] paramTypes, Object[] listInitArgs) {
        this.impl = impl;
        this.paramTypes = paramTypes;
        this.listInitArgs = listInitArgs;
        this.singletonObject = null;
    }

    public ProviderImpl(Class<T> impl) {
        this.impl = impl;
        this.paramTypes = null;
        this.listInitArgs = null;
        this.singletonObject = null;
    }

    public ProviderImpl(Class<T> impl, T singletonObject) {
        this.impl = impl;
        this.paramTypes = null;
        this.listInitArgs = null;
        this.singletonObject = singletonObject;
    }

    @Override
    public T getInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(impl.isAnnotationPresent(Singleton.class) && singletonObject != null)
            return singletonObject;

        return impl.getDeclaredConstructor(paramTypes).newInstance(listInitArgs);
    }
}
