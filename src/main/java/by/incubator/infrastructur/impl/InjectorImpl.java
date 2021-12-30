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
    private final Map<String, Class> bindCache = new HashMap<>();

    @Override
    public <T> Provider<T> getProvider(Class<T> type) {

        return new ProviderImpl<>(bindCache.get(type.getSimpleName()));
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) {
        bindCache.put(intf.getSimpleName(), impl);
    }

    @Override
    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {

    }
}