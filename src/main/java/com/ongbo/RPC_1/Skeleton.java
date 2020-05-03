package com.ongbo.RPC_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器代理实现类
 * */
public class Skeleton {
    private IHello hello = new HelloService();
    public void publishrServer(int port){
        try(ServerSocket ss = new ServerSocket(port)){
           while(true){
               //循环等待客户端的请求
               try(Socket socket = ss.accept()){
                   //输入流
                   ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                   //读取请求的方法名
                   String method = ois.readUTF();
                   Object[] objs = (Object[]) ois.readObject();
                   //读取参数名
                   Class<?>[] types = new Class[objs.length];
                   for(int i=0;i<types.length;i++){
                       types[i] = objs.getClass();
                   }
                   //调用方法
                   System.out.println(method+"  "+types);
                   Method m = HelloService.class.getMethod(method,types);
                   Object obj = m.invoke(hello,objs);

                   //输出结果
                   ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                   oos.writeObject(obj);
                   oos.flush();

               }catch (Exception e){
                   e.printStackTrace();
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
