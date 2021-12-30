package by.incubator.infrastructur.impl;

import by.incubator.infrastructur.Injector;
import by.incubator.infrastructur.Provider;
import by.incubator.infrastructur.exception.ConstructorNotFoundException;
import by.incubator.infrastructur.exception.TooManyConstructorsException;
import by.incubator.modelForTest.*;
import by.incubator.modelForTest.impl.*;
import junit.framework.TestCase;


public class InjectorImplTest extends TestCase {

    public void testGetProvider() throws TooManyConstructorsException, ConstructorNotFoundException {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);

        Provider<TestInterface> provider = injector.getProvider(TestInterface.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(TestInterfaceImpl.class, provider.getInstance().getClass());
    }

    public void testGetProviderForConstructorWithParameter() throws TooManyConstructorsException, ConstructorNotFoundException {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);
        injector.bind(InterfaceForConstructorWithParameters.class, ConstructorWithParameters.class);

        Provider<InterfaceForConstructorWithParameters> provider = injector.getProvider(InterfaceForConstructorWithParameters.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(ConstructorWithParameters.class, provider.getInstance().getClass());
    }

    public void testTooManyConstructors() {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);
        injector.bind(TooManyConstructorsInterface.class, TooManyConstructorsImp.class);

        try {
            Provider<TooManyConstructorsInterface> provider = injector.getProvider(TooManyConstructorsInterface.class);
            fail("exception doesn't catch");
        } catch (TooManyConstructorsException | ConstructorNotFoundException e) {
            assertEquals(e.getMessage(),"The class contains more than one annotated constructor @Inject");
        }
    }

    public void testConstructorNotFound() {
        Injector injector = new InjectorImpl();
        injector.bind(ConstructorNotFoundInterface.class, ConstructorNotFoundImpl.class);

        try {
            Provider<ConstructorNotFoundInterface> provider = injector.getProvider(ConstructorNotFoundInterface.class);
            fail("exception doesn't catch");
        } catch (TooManyConstructorsException | ConstructorNotFoundException e) {
            assertEquals(e.getMessage(),"The class doesn't contain any annotated constructor @Inject");
        }
    }
}