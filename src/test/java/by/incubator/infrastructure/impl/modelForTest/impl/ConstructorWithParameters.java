package by.incubator.infrastructure.impl.modelForTest.impl;

import by.incubator.infrastructure.annotation.Inject;
import by.incubator.infrastructure.impl.modelForTest.InterfaceForConstructorWithParameters;
import by.incubator.infrastructure.impl.modelForTest.TestInterface;

public class ConstructorWithParameters implements InterfaceForConstructorWithParameters {
    TestInterface testInterface;

    @Inject
    public ConstructorWithParameters(TestInterface testInterface){
        this.testInterface = testInterface;
    }
}
