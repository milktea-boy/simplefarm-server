package org.milkteaboy.simplefarm.netty.constant;

/**
 * 消息错误类型
 */
public enum SocketErrorType {

    METHODORARGS_ERROR("接口名或参数错误"), METHOD_ERROR("接口名错误"), ARGS_ERROR("参数列表错误");

    private String name;

    private SocketErrorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
