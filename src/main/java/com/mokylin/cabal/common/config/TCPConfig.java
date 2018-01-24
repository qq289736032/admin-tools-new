package com.mokylin.cabal.common.config;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/3/31 17:33
 * 项目: admin-tools
 */
public class TCPConfig {

    private int port;

    public TCPConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
