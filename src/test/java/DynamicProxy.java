import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by niceyuanze on 17-5-23.
 */
public class DynamicProxy implements InvocationHandler{

    private Object target;


    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);

    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);

        Hello helloProxy =
                (Hello) Proxy.newProxyInstance(
                        hello.getClass().getClassLoader(),
                        hello.getClass().getInterfaces(),
                        dynamicProxy);
        helloProxy.say("Jack");

        System.out.println();
        System.out.println();
        System.out.println();

        Hello helloProxy1 = dynamicProxy.getProxy();
        helloProxy1.say("Jack111");
    }


}
