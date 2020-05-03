package com.ongbo.RPC_2.Tool;

import java.lang.reflect.Proxy;

/**
 * 客户端获取代理对象
 * 客户端代理
 * */
public class RpcClientProxy {
    public <T> T clientProxy(final Class<T> interfaceClass,final String host,final int port){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},new RemoteInvocationHandler(host,port));
    }
}
