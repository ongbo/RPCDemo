package com.ongbo.RPC_2.Tool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {
    /**线程池*/
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    public void publisher(final Object service,int port){
        /**启动服务监听*/
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                executor.execute(new ProcessHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
