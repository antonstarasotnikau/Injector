package by.incubator.infrastructur.impl;

import by.incubator.infrastructur.Injector;
import by.incubator.infrastructur.Provider;
import by.incubator.infrastructur.annotation.Inject;
import by.incubator.modelForTest.InterfaceForConstructorWithParameters;
import by.incubator.modelForTest.TestInterface;
import by.incubator.modelForTest.impl.ConstructorWithParameters;
import by.incubator.modelForTest.impl.TestInterfaceImpl;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class InjectorImplTest extends TestCase {

    public void testGetProvider() {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);

        Provider<TestInterface> provider = injector.getProvider(TestInterface.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(TestInterfaceImpl.class, provider.getInstance().getClass());
    }

    public void testGetProviderForConstructorWithParameter() {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);
        injector.bind(InterfaceForConstructorWithParameters.class, ConstructorWithParameters.class);

        Provider<InterfaceForConstructorWithParameters> provider = injector.getProvider(InterfaceForConstructorWithParameters.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(ConstructorWithParameters.class, provider.getInstance().getClass());
    }
}