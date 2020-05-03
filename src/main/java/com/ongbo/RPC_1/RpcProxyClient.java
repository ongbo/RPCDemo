package com.ongbo.RPC_1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RpcProxyClient<T> {
    public T proxyClient(Class<T> clazz){
        return (T) (Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //构建一个Socket，连接远程服务
                Socket socket = new Socket("localhost",8000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //向远程发送数据
                oos.writeUTF(method.getName());
                oos.writeObject(args);
                oos.flush();
                //接受远程返回的相应数据
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                return ois.readObject();
            }
        }));
    }
}
