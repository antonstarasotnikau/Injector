package by.incubator.infrastructure;

import by.incubator.infrastructure.exception.BindingNotFoundException;
import by.incubator.infrastructure.exception.ConstructorNotFoundException;
import by.incubator.infrastructure.exception.TooManyConstructorsException;

import java.lang.reflect.InvocationTargetException;

public interface Injector {
    <T> Provider<T> getProvider(Class<T> type) throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    <T> void bind(Class<T> intf, Class<? extends T> impl);
    <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
