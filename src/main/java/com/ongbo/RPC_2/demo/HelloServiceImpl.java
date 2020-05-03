package com.ongbo.RPC_2.demo;

import com.ongbo.RPC_1.IHello;

public class HelloServiceImpl implements IHello {
    @Override
    public String sayHello(String info) {
       return "你好："+info;
    }
}
