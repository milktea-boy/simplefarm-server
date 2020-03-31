package org.milkteaboy.simplefarm.netty.constant;

/**
 * Socket激活类型
 */
public enum SocketActiveType {
    ACTIVE("激活"), INACTIVE("非激活");

    SocketActiveType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
