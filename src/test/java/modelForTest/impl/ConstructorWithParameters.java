package modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import modelForTest.InterfaceForConstructorWithParameters;
import modelForTest.TestInterface;

public class ConstructorWithParameters implements InterfaceForConstructorWithParameters {
    TestInterface testInterface;

    @Inject
    public ConstructorWithParameters(TestInterface testInterface){
        this.testInterface = testInterface;
    }
}
