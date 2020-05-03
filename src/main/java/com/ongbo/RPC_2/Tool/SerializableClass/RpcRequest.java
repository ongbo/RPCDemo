package com.ongbo.RPC_2.Tool.SerializableClass;

import java.io.Serializable;

/**
 * 用来序列化，告诉服务器端需要做什么
 * 统一传输对象
 * */
public class RpcRequest implements Serializable {
    /**
     * 类名，方法名，和参数
     * */
    private String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
