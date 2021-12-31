package modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import modelForTest.ParameterWithoutBindingInterface;
import modelForTest.TestInterface;

public class ParameterWithoutBindingImpl implements ParameterWithoutBindingInterface {

    TestInterface testInterface;

    @Inject
    public ParameterWithoutBindingImpl(TestInterface testInterface){

    }
}
