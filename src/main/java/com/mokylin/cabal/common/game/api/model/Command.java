package com.mokylin.cabal.common.game.api.model;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/12/24
 * admin-tools
 */
public class Command {

    private int command;
    private boolean status;// true有效，false 无效

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
