package by.incubator.infrastructur.impl.modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import by.incubator.infrastructur.impl.modelForTest.TestInterface;
import by.incubator.infrastructur.impl.modelForTest.TooManyConstructorsInterface;

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
