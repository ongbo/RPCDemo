package com.ongbo.RPC_3.Registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
public class RegistryCenterImpl implements IRegistryCenter {

    private CuratorFramework curatorFramework;
    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(RegistryCenterConfig.CONNECTING_STR)
                .sessionTimeoutMs(RegistryCenterConfig.SESSION_TIMEOUT)
                .retryPolicy(new ExponentialBackoffRetry(1000,10)).build();
        curatorFramework.start();
    }
    @Override
    public void register(String serviceName, String serviceAddress) {
        /**服务在注册中心NameSpace的名称*/
        String serviceNodePath = RegistryCenterConfig.NAMESPACE+"/"+serviceName;
        try {
            if(curatorFramework.checkExists().forPath(serviceNodePath)==null){
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(serviceNodePath,RegistryCenterConfig.DEFAULT_VALUE);

            }
            //注册服务的节点路径
            String addressPath = serviceNodePath +"/"+serviceAddress;
            //临时节点
            String rsNode = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .forPath(addressPath,RegistryCenterConfig.DEFAULT_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
