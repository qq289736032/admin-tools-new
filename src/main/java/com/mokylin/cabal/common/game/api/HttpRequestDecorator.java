package com.mokylin.cabal.common.game.api;

import com.mokylin.cabal.common.utils.URIBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/22 13:10
 * 项目: admin-tools
 */
public class HttpRequestDecorator extends HttpRequestWrapper {

    private HttpHeaders httpHeaders;

    private boolean existingHeadersAdded;

    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

    public HttpRequestDecorator(HttpRequest request) {
        super(request);
    }

    public void addParameter(String name, String value) {
        parameters.add(name, value);
    }

    public HttpHeaders getHeaders() {
        if (!existingHeadersAdded) {
            this.httpHeaders = new HttpHeaders();
            httpHeaders.putAll(getRequest().getHeaders());
            existingHeadersAdded = true;
        }
        return httpHeaders;
    }

    @Override
    public URI getURI() {
        if (parameters.isEmpty()) {
            return super.getURI();
        }
        return URIBuilder.fromUri(super.getURI()).queryParams(parameters).build();
    }

}