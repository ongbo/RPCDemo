package com.ongbo.RPC_2.Tool;

import com.ongbo.RPC_2.Tool.SerializableClass.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;

public class ProcessHandler implements Runnable {
    private Socket socket;

    /***
     * 服务端发布的服务
     * */
    private Object service;
    /**
     * 构造方法，顺便保存socket和服务service
     * */
    public ProcessHandler(Socket socket,Object service){
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            /**反序列化，将网络输入流的序列化对象反序列化成RpcRequest*/
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object result = invoke(rpcRequest);
            /**将结果返回给客户端*/
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if(objectInputStream!=null){
                try {
                    /**关闭输入流*/
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /***
     * 反射调用
     * */
    private Object invoke(RpcRequest rpcRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("服务端开始调用了......");
        System.out.println("调用的为："+rpcRequest.getClassName()+" "+rpcRequest.getMethodName()+" ");
        Object[] parameters = rpcRequest.getParameters();
        /**
         * 两种参数类型的选择方式，可以随便选择，第二种要使用jdk1.8以上
         * */
        Class[] parameterTypes = new Class[parameters.length];
        Class<?>[] parameTypes = Arrays.stream(parameters).map(Object::getClass).toArray(Class<?>[]::new);
        for(int i=0,length = parameters.length;i<length;i++){
            parameterTypes[i] = parameters[i].getClass();
            System.out.println(parameterTypes[i].getName());
        }
//        for(int i=0;i<parameTypes.length;i++) System.out.println(parameTypes[i]);
        /**开始调用方法*/
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(),parameterTypes);
        return method.invoke(service,parameters);
    }
}
