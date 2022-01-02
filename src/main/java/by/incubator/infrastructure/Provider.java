package by.incubator.infrastructure;

import java.lang.reflect.InvocationTargetException;

public interface Provider <T>{
    T getInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
