package com.mokylin.cabal.common.game.api;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mokylin.cabal.common.game.api.impl.*;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/30 20:42
 * 项目: cabal-tools
 */
public class GameTemplate implements Game {

    private AnnounceOperation announceOperation;

    private GameEmailOperation gameEmailOperation;

    private TreasureOperation treasureOperation;

    private RoleOperation roleOperation;

    private MonitorOperation monitorOperation;

    private GuildOperation guildOperation;

    private VipQqOperation vipQqOperation;

    private ToolOperation toolOperation;

    private AsyncRestTemplate restTemplate;

    private RestTemplate template;

    public AsyncRestTemplate getRestTemplate() {
        return restTemplate;
    }

    public RestTemplate getTemplate(){
        return template;
    }

    public void setRestTemplate(AsyncRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GameTemplate() {
        restTemplate = configureRestTemplate();
        template = configureTemplate();
        initialize();
    }

    /**
     * http url 后面增加参数 access_token
     * @param accessToken
     */
    public GameTemplate(String accessToken) {
        restTemplate = configureRestTemplate(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        initialize();
    }

    protected RestTemplate configureTemplate(){
        RestTemplate client;
        List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
        client = new RestTemplate();
        client.setMessageConverters(messageConverters);
        return client;
    }

    protected  AsyncRestTemplate configureRestTemplate(String accessToken, TokenStrategy tokenStrategy){

        RestTemplate client;
        List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
        client = new RestTemplate();
        client.setMessageConverters(messageConverters);
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(5000);
        client.setRequestFactory(httpRequestFactory);

        ClientHttpRequestInterceptor interceptor = tokenStrategy.interceptor(accessToken);
        List<ClientHttpRequestInterceptor> interceptors = new LinkedList<ClientHttpRequestInterceptor>();
        interceptors.add(interceptor);
        client.setInterceptors(interceptors);

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setTaskExecutor(new SimpleAsyncTaskExecutor());

        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(requestFactory,client);

        return asyncRestTemplate;
    }


    protected AsyncRestTemplate configureRestTemplate(){
        AsyncRestTemplate client;
        List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
        client = new AsyncRestTemplate();
        client.setMessageConverters(messageConverters);
        //client.setAsyncRequestFactory(new SimpleClientHttpRequestFactory());
        return client;
    }

    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(getFastJsonMessageConverter());
        return messageConverters;
    }

    protected FastJsonHttpMessageConverter getFastJsonMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(new MediaType("text", "plain", Charset.forName("UTF-8")));
        mediaTypeList.add(new MediaType("application", "json", Charset.forName("UTF-8")));
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        return fastJsonHttpMessageConverter;
    }

    private void initialize() {
        this.announceOperation = new AnnounceTemplate(getRestTemplate());
        this.gameEmailOperation = new GameEmailTemplate(getRestTemplate());
        this.treasureOperation = new TreasureTemplate(getRestTemplate());
        this.roleOperation = new RoleTemplate(getRestTemplate(), getTemplate());
        this.monitorOperation = new MonitorTemplate(getRestTemplate());
        this.guildOperation= new GuildTemplate(getRestTemplate());
        this.vipQqOperation = new VipQqTemplate(getRestTemplate());
        this.toolOperation = new ToolTemplate(getRestTemplate());
    }

    @Override
    public AnnounceOperation announceOperation() {
        return announceOperation;
    }

    @Override
    public GameEmailOperation gameEmailOperation() {
        return gameEmailOperation;
    }

    @Override
    public TreasureOperation treasureOperation() {
        return treasureOperation;
    }

    @Override
    public RoleOperation roleOperation() {
        return roleOperation;
    }

    @Override
    public MonitorOperation monitorOperation() {
        return monitorOperation;
    }

    @Override
    public GuildOperation guildOperation() {
        return guildOperation;
    }

    @Override
    public VipQqOperation vipQqOperation() {
        return vipQqOperation;
    }

    public ToolOperation getToolOperation() {
        return toolOperation;
    }
}
