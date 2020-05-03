package com.ongbo.RPC_2.demo;

import com.ongbo.RPC_2.Tool.RpcServer;

public class ServerDemo {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(new HelloServiceImpl(),12345);
    }
}
