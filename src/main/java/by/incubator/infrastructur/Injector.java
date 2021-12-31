package by.incubator.infrastructur;

import by.incubator.infrastructur.exception.BindingNotFoundException;
import by.incubator.infrastructur.exception.ConstructorNotFoundException;
import by.incubator.infrastructur.exception.TooManyConstructorsException;

import java.lang.reflect.InvocationTargetException;

public interface Injector {
    <T> Provider<T> getProvider(Class<T> type) throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    <T> void bind(Class<T> intf, Class<? extends T> impl);
    <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
