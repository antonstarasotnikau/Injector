package by.incubator.infrastructur.impl.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.infrastructur.impl.modelForTest.InterfaceForConstructorWithParameters;
import by.incubator.infrastructur.impl.modelForTest.TestInterface;

public class ConstructorWithParameters implements InterfaceForConstructorWithParameters {
    TestInterface testInterface;

    @Inject
    public ConstructorWithParameters(TestInterface testInterface){
        this.testInterface = testInterface;
    }
}
