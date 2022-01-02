package by.incubator.infrastructure.impl.modelForTest.impl;

import by.incubator.infrastructure.annotation.Inject;
import by.incubator.infrastructure.impl.modelForTest.ParameterWithoutBindingInterface;
import by.incubator.infrastructure.impl.modelForTest.TestInterface;

public class ParameterWithoutBindingImpl implements ParameterWithoutBindingInterface {

    TestInterface testInterface;

    @Inject
    public ParameterWithoutBindingImpl(TestInterface testInterface){

    }
}
