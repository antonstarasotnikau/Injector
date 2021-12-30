package by.incubator.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.modelForTest.InterfaceForConstructorWithParameters;
import by.incubator.modelForTest.TestInterface;

public class ConstructorWithParameters implements InterfaceForConstructorWithParameters {
    TestInterface testInterface;

    @Inject
    public ConstructorWithParameters(TestInterface testInterface){
        this.testInterface = testInterface;
    }
}
