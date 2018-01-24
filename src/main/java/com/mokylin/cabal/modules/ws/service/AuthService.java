package com.mokylin.cabal.modules.ws.service;

import com.mokylin.cabal.common.utils.Md5Utils;
import org.springframework.stereotype.Service;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/15 11:14
 * 项目: admin-tools
 */
public class AuthService {

    public static final String AUTH_CODE = "c33048704bc96a45392f4f86ddd43f27";

    public boolean checkClientId(String sign){
        boolean flag = false;

        return flag;
    }

    public static void main(String[] args) {
        System.out.println(Md5Utils.md5To16("mars20150215c33048704bc96a45392f4f86ddd43f27"));
    }
}
