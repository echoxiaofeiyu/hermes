package com.fish.hermes.support.service.impl;

import cn.hutool.setting.dialect.Props;
import com.fish.hermes.support.service.ConfigService;
import com.fish.hermes.support.utils.NacosUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author :  shenzhenxing
 * @Date :  2022/11/19 14:08
 */
public class ConfigServiceImpl implements ConfigService {


    /**
     * nacos配置
     */
    @Value("${hermes.nacos.enabled}")
    private Boolean enableNacos;

    @Resource
    private NacosUtils nacosUtils;

    /**
     * 本地配置
     */
    private static final String PROPERTIES_PATH = "local.properties";
    private final Props props = new Props(PROPERTIES_PATH, StandardCharsets.UTF_8);

    @Override
    public String getProperty(String key, String defaultValue) {
        if(enableNacos){
            return nacosUtils.getProperty(key, defaultValue);
        }
        return props.getProperty(key, defaultValue);
    }
}
