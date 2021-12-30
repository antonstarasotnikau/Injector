package by.incubator.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.modelForTest.TestInterface;
import by.incubator.modelForTest.TooManyConstructorsInterface;

public class TooManyConstructorsImp implements TooManyConstructorsInterface {
    TestInterface testInterface;

    @Inject
    public TooManyConstructorsImp() {
    }

    @Inject
    public TooManyConstructorsImp(TestInterface testInterface){
        this.testInterface = testInterface;
    }
}
