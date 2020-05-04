package com.ongbo.RPC_2.Tool;

import com.ongbo.RPC_2.Tool.SerializableClass.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    /**发起客户端和服务器的远程调用，调用客户端的信息进行传输*/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //构建一个Rpc传输对象RpcRequest，里面包括了类名，方法名和参数
        System.out.println("proxy:"+proxy.getClass().getName());

        System.out.println("method:"+method.getName());
        System.out.println(args[0]);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        System.out.println(args[0].getClass());

        /**将这个Rpc调用对象通过tcp传输过去*/
        TcpTransport tcpTransport = new TcpTransport(host,port);
        return tcpTransport.send(rpcRequest);
    }

    /**构造方法*/
    public RemoteInvocationHandler(String host,int port){
        this.host = host;
        this.port = port;
    }
}
