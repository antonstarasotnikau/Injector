package by.incubator.infrastructur.impl.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.infrastructur.annotation.Singleton;
import by.incubator.infrastructur.impl.modelForTest.TestInterface;
@Singleton
public class SingletonImpl implements TestInterface {
    @Inject
    public SingletonImpl() {}
}
