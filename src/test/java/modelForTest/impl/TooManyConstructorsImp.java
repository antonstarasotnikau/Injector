package modelForTest.impl;

import by.incubator.infrastructur.annotation.Inject;
import modelForTest.TestInterface;
import modelForTest.TooManyConstructorsInterface;

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
