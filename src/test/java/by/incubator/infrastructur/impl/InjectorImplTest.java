package by.incubator.infrastructur.impl;

import by.incubator.infrastructur.Injector;
import by.incubator.infrastructur.Provider;
import by.incubator.infrastructur.exception.*;
import junit.framework.TestCase;
import modelForTest.*;
import modelForTest.impl.*;


public class InjectorImplTest extends TestCase {

    public void testGetProvider() throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);

        Provider<TestInterface> provider = injector.getProvider(TestInterface.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(TestInterfaceImpl.class, provider.getInstance().getClass());
    }

    public void testGetProviderForConstructorWithParameter() throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);
        injector.bind(InterfaceForConstructorWithParameters.class, ConstructorWithParameters.class);

        Provider<InterfaceForConstructorWithParameters> provider = injector.getProvider(InterfaceForConstructorWithParameters.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(ConstructorWithParameters.class, provider.getInstance().getClass());
    }

    public void testTooManyConstructors() throws  ConstructorNotFoundException, BindingNotFoundException{
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInterfaceImpl.class);
        injector.bind(TooManyConstructorsInterface.class, TooManyConstructorsImp.class);

        try {
            Provider<TooManyConstructorsInterface> provider = injector.getProvider(TooManyConstructorsInterface.class);
            fail("exception doesn't catch");
        } catch (TooManyConstructorsException e) {
            assertEquals(e.getMessage(),"The class contains more than one annotated constructor @Inject");
        }
    }

    public void testConstructorNotFound() throws TooManyConstructorsException, BindingNotFoundException{
        Injector injector = new InjectorImpl();
        injector.bind(ConstructorNotFoundInterface.class, ConstructorNotFoundImpl.class);

        try {
            Provider<ConstructorNotFoundInterface> provider = injector.getProvider(ConstructorNotFoundInterface.class);
            fail("exception doesn't catch");
        } catch (ConstructorNotFoundException e) {
            assertEquals(e.getMessage(),"The class doesn't contain any annotated constructor @Inject");
        }
    }

    public void testBindingNotFound() throws TooManyConstructorsException, ConstructorNotFoundException{
        Injector injector = new InjectorImpl();
        injector.bind(ParameterWithoutBindingInterface.class, ParameterWithoutBindingImpl.class);

        try {
            Provider<ParameterWithoutBindingInterface> provider = injector.getProvider(ParameterWithoutBindingInterface.class);
            fail("exception doesn't catch");
        } catch (BindingNotFoundException e) {
            assertEquals(e.getMessage(),"Type doesn't have bind in map");
        }
    }

    public void testEmptyBind() throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException {
        Injector injector = new InjectorImpl();

        Provider<TestInterface> provider = injector.getProvider(TestInterface.class);

        assertNull(provider);
    }

    public void testSingleton() throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, SingletonImpl.class);
        injector.bindSingleton(TestInterface.class, SingletonImpl.class);

        Provider<TestInterface> provider = injector.getProvider(TestInterface.class);
        Provider<TestInterface> provider2 = injector.getProvider(TestInterface.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(SingletonImpl.class, provider.getInstance().getClass());
        assertSame(provider.getInstance().getClass(), provider2.getInstance().getClass());
    }

    public void testSingletonLazyInitialization() throws TooManyConstructorsException, ConstructorNotFoundException, BindingNotFoundException {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, SingletonImpl.class);

        Provider<TestInterface> provider = injector.getProvider(TestInterface.class);
        Provider<TestInterface> provider2 = injector.getProvider(TestInterface.class);

        assertNotNull(provider);
        assertNotNull(provider.getInstance());
        assertSame(SingletonImpl.class, provider.getInstance().getClass());
        assertSame(provider.getInstance().getClass(), provider2.getInstance().getClass());
    }

}