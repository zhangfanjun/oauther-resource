package com.zhang.oauther2.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Copyright
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauther2")
public class Oauther2Properties {
    private Map<String,String[]> resourceMap;
}
