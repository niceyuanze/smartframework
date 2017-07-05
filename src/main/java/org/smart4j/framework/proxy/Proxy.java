package org.smart4j.framework.proxy;

/**
 * Created by niceyuanze on 17-5-25.
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
