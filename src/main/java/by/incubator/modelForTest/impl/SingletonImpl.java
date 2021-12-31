package by.incubator.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.infrastructur.annotation.Singleton;
import by.incubator.modelForTest.TestInterface;
@Singleton
public class SingletonImpl implements TestInterface {
    @Inject
    public SingletonImpl() {}
}
