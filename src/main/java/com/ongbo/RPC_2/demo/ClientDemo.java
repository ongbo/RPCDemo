package com.ongbo.RPC_2.demo;

import com.ongbo.RPC_2.Tool.RpcClientProxy;

public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy();
        IHelloServie helloServie = proxy.clientProxy(IHelloServie.class,"localhost",12345);

        String name = helloServie.sayHello("ongbo");
        System.out.println(name);
        helloServie.sayInt("ongboinfo",1234);
    }
}
