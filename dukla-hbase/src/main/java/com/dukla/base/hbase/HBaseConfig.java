package com.dukla.base.hbase;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author: dukla.ou
 * @Date: 2019/10/22
 */
@Configuration
@ConfigurationProperties(prefix = "hbase.conf")
public class HBaseConfig {

    private Map<String, String> confMaps;

    public Map<String, String> getConfMaps() {
        return confMaps;
    }

    public void setConfMaps(Map<String, String> confMaps) {
        this.confMaps = confMaps;
    }
}
