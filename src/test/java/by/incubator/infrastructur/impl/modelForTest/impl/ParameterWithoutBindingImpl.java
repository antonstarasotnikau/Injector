package by.incubator.infrastructur.impl.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.infrastructur.impl.modelForTest.ParameterWithoutBindingInterface;
import by.incubator.infrastructur.impl.modelForTest.TestInterface;

public class ParameterWithoutBindingImpl implements ParameterWithoutBindingInterface {

    TestInterface testInterface;

    @Inject
    public ParameterWithoutBindingImpl(TestInterface testInterface){

    }
}
