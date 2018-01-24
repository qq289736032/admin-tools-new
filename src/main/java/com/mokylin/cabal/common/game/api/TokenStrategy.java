package com.mokylin.cabal.common.game.api;

import org.springframework.http.client.ClientHttpRequestInterceptor;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/22 13:06
 * 项目: admin-tools
 */
public enum TokenStrategy {


    ACCESS_TOKEN_PARAMETER {
        public ClientHttpRequestInterceptor interceptor(String accessToken) {
            return new TokenParameterRequestInterceptor(accessToken);
        }
    };

    abstract ClientHttpRequestInterceptor interceptor(String accessToken);
}
