import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by niceyuanze on 17-5-23.
 */
public class CGLibProxy implements MethodInterceptor{

    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy(){

    }

    public static CGLibProxy getInstance(){
        return instance;
    }


    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    public static void main(String[] args) {
        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("LLALALALALAL");
//        CGLibProxy cgLibProxy = new CGLibProxy();
//        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
//        helloProxy.say("huainian");
        int y =3;
        int z = y > 3?1:2;
    }


    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }
}
