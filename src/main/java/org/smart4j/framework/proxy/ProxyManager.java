package org.smart4j.framework.proxy;

/**
 * Created by niceyuanze on 17-5-25.
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器
 *
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<?> targetClass,
                                    final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o,
                                    Method method,
                                    Object[] objects,
                                    MethodProxy methodProxy) throws Throwable {

                return new ProxyChain(targetClass, o, method,methodProxy,objects,proxyList);

            }
        });
    }
}
