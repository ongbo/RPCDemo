package com.ongbo.RPC_3.Registry;

public interface RegistryCenterConfig {
    /**zookeeper地址*/
    String CONNECTING_STR="192.168.220....";
    int SESSION_TIMEOUT = 4000;
    /**注册中心的namespace*/
    String NAMESPACE="/rpcNode";
    byte[] DEFAULT_VALUE="0".getBytes();
}
