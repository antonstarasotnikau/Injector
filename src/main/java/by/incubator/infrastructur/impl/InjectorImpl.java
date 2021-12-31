package by.incubator.infrastructur.impl;

import by.incubator.infrastructur.Injector;
import by.incubator.infrastructur.Provider;
import by.incubator.infrastructur.annotation.Inject;
import by.incubator.infrastructur.annotation.Singleton;
import by.incubator.infrastructur.exception.BindingNotFoundException;
import by.incubator.infrastructur.exception.ConstructorNotFoundException;
import by.incubator.infrastructur.exception.TooManyConstructorsException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjectorImpl implements Injector {
    private final Map<String, Class<?>> bindCache = new HashMap<>();
    private final Map<String, Object> bindSingletonCache = new HashMap<>();

    @Override
    public <T> Provider<T> getProvider(Class<T> type) throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        synchronized (type) {
            if (bindCache.containsKey(type.getSimpleName())) {
                Class<T> impl = (Class<T>) bindCache.get(type.getSimpleName());
                if (impl.isAnnotationPresent(Singleton.class)) {
                    if (!bindSingletonCache.containsKey(type.getSimpleName()))
                        bindSingleton(type, impl);
                    return new ProviderImpl<>(impl, (T) bindSingletonCache.get(type.getSimpleName()));
                }
                return createProvider(impl);
            }
        }
        return null;
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) {
        bindCache.put(intf.getSimpleName(), impl);
    }

    @Override
    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        bindSingletonCache.put(intf.getSimpleName(), createProvider(impl).getInstance());
    }

    private <T> Provider<T> createProvider(Class<T> impl) throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<?>[] paramTypes = getParamTypes(impl);
        if(paramTypes.length != 0) {
            Object[] listInitArgs = getListInitArgs(paramTypes);
            return new ProviderImpl<>(impl, paramTypes, listInitArgs);
        }
        return new ProviderImpl<>(impl);
    }

    private Class<?>[] getParamTypes(Class<?> impl) throws TooManyConstructorsException, ConstructorNotFoundException {
        Class<?>[] paramTypes;
        List<Constructor<?>> constructorList = new ArrayList<>(List.of(impl.getDeclaredConstructors()));
        constructorList.removeIf(constructor -> !constructor.isAnnotationPresent(Inject.class));
        if(constructorList.size() > 1)
            throw new TooManyConstructorsException("The class contains more than one annotated constructor @Inject");
        if(constructorList.size() == 0)
            throw new ConstructorNotFoundException("The class doesn't contain any annotated constructor @Inject");
        paramTypes = constructorList.get(0).getParameterTypes();
        return paramTypes;
    }

    private Object[] getListInitArgs(Class<?>[] paramTypes) throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Object> listInitArgs = new ArrayList<>();
        for (Class<?> pType: paramTypes)
            if (!bindCache.containsKey(pType.getSimpleName())) {
                throw new BindingNotFoundException("Type doesn't have bind in map");
            } else {
                listInitArgs.add(getProvider(pType).getInstance());
            }

        return  listInitArgs.toArray();
    }
}