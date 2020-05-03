package com.ongbo.RPC_1;
/**
 * 远程服务接口实现类
 * */
public class HelloService implements IHello {
    public String sayHello(String info) {
        String result = "hello : " + info;
        System.out.println(result);
        return result;
    }
}
