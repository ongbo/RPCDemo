package com.ongbo.RPC_2.Tool;

import com.ongbo.RPC_2.Tool.SerializableClass.RpcRequest;
import com.ongbo.Tool.DatePrint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Socket传输
 * */
public class TcpTransport {
    private String host;
    private int port;
    public TcpTransport(String host,int port){
        this.host = host;
        this.port = port;
    }
    private Socket newSocket(){
        DatePrint.printdate();
        System.out.println("准备创建Socket连接，host："+host+"  Port："+port);
        Socket socket = null;
        try {
            socket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
    public Object send(RpcRequest rpcRequest){
        /**建立Socket连接*/
        Socket socket = null;
        socket = newSocket();

        /**向这个socket的输出流写入序列化的Rpc请求对象*/
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**输入流，接受socket返回的结果*/
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            inputStream.close();
            outputStream.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       return null;

    }
}
