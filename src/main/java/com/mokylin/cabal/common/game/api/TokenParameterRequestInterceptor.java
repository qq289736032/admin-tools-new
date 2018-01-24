package com.mokylin.cabal.common.game.api;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/22 13:08
 * 项目: admin-tools
 */
public class TokenParameterRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String parameterName;

    private final String accessToken;

    public TokenParameterRequestInterceptor(String accessToken) {
        this(accessToken, "access_token");
    }

    public TokenParameterRequestInterceptor(String accessToken, String parameterName) {
        this.accessToken = accessToken;
        this.parameterName = parameterName;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestDecorator protectedResourceRequest = new HttpRequestDecorator(request);
        protectedResourceRequest.addParameter(parameterName, accessToken);
        return execution.execute(protectedResourceRequest, body);
    }
}
