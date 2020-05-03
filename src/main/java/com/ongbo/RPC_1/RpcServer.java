package com.ongbo.RPC_1;

public class RpcServer {
    public static void main(String[] args) {
        Skeleton server = new Skeleton();
        server.publishrServer(8000);
    }
}
