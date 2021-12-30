package by.incubator.infrastructur;

import by.incubator.infrastructur.exception.TooManyConstructorsException;

public interface Injector {
    <T> Provider<T> getProvider(Class<T> type) throws TooManyConstructorsException;
    <T> void bind(Class<T> intf, Class<? extends T> impl);
    <T> void bindSingleton(Class<T> intf, Class<? extends T> impl);
}
