package by.incubator.infrastructur;

import java.lang.reflect.InvocationTargetException;

public interface Provider <T>{
    T getInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
