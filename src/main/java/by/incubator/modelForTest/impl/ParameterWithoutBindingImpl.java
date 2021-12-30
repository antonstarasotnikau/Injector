package by.incubator.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.modelForTest.ParameterWithoutBindingInterface;
import by.incubator.modelForTest.TestInterface;

public class ParameterWithoutBindingImpl implements ParameterWithoutBindingInterface {

    TestInterface testInterface;

    @Inject
    public ParameterWithoutBindingImpl(TestInterface testInterface){

    }
}
