package by.incubator.infrastructure.impl.modelForTest.impl;

import by.incubator.infrastructure.annotation.Inject;
import by.incubator.infrastructure.impl.modelForTest.TestInterface;
import by.incubator.infrastructure.impl.modelForTest.TooManyConstructorsInterface;

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
