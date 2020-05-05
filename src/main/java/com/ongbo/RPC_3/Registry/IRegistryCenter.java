package com.ongbo.RPC_3.Registry;

public interface IRegistryCenter {
    /***
     * serverName:服务名称
     * serviceAddress：服务地址
     * */
    void register(String serviceName,String serviceAddress);
}
