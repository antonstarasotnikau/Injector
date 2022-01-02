package by.incubator.infrastructure.impl.modelForTest.impl;

import by.incubator.infrastructure.annotation.Inject;
import by.incubator.infrastructure.annotation.Singleton;
import by.incubator.infrastructure.impl.modelForTest.TestInterface;
@Singleton
public class SingletonImpl implements TestInterface {
    @Inject
    public SingletonImpl() {}
}
