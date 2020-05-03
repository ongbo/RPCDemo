package com.ongbo.RPC_1;

public class RpcClient {
    public static void main(String[] args) {
        RpcProxyClient<HelloService> rpcClient = new RpcProxyClient<>();
        IHello hello = rpcClient.proxyClient(HelloService.class);
        String s = hello.sayHello("dd");
        System.out.println(s);
    }
}
