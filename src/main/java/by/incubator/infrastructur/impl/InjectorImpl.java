package by.incubator.infrastructur.impl;

import by.incubator.infrastructur.Injector;
import by.incubator.infrastructur.Provider;
import by.incubator.infrastructur.annotation.Inject;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjectorImpl implements Injector {
    private final Map<String, Class<?>> bindCache = new HashMap<>();

    @Override
    public <T> Provider<T> getProvider(Class<T> type) {
        return (Provider<T>) createProvider(bindCache.get(type.getSimpleName()));
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) {
        bindCache.put(intf.getSimpleName(), impl);
    }

    @Override
    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {

    }

    private <T> Provider<T> createProvider(Class<T> impl) {
        Provider<T> provider = null;
        Class<?>[] paramTypes = getParamTypes(impl);
        if(paramTypes.length != 0) {
            Object[] listInitArgs = getListInitArgs(paramTypes);
            return new ProviderImpl<>(impl, paramTypes, listInitArgs);
        }
        return new ProviderImpl<>(impl);
    }

    private Class<?>[] getParamTypes(Class<?> impl) {
        Class<?>[] paramTypes;
        List<Constructor<?>> constructorList = new ArrayList<>(List.of(impl.getDeclaredConstructors()));
        constructorList.removeIf(constructor -> !constructor.isAnnotationPresent(Inject.class));
        paramTypes = constructorList.get(0).getParameterTypes();
        return paramTypes;
    }

    private Object[] getListInitArgs(Class<?>[] paramTypes) {
        List<Object> listInitArgs = new ArrayList<>();
        for (Class<?> pType: paramTypes)
            listInitArgs.add(getProvider(pType).getInstance());
        return  listInitArgs.toArray();
    }
}