package com.mokylin.cabal.common.persistence;

import java.io.Serializable;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/22 11:57
 * 项目: cabal-tools
 */
public class Result implements Serializable{

    private static final long serialVersionUID = -6796913034844000040L;

    private boolean success; // 成功标志
    private int error;       // 错误代码
    private Object data;     // 相关数据

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result error(int error) {
        this.error = error;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
}
