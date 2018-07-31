package cn.cloudx.namevoice.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.TreeMap;

/**
 * 腾讯云配置
 *
 * @author zhanghao
 * @date 2018/06/24
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "tencent-cloud")
public class TencentCloudConfig {
    private String secretId;
    private String secretKey;
    private String requestMethod;
    private String defaultRegion;

    public TreeMap<String, Object> config() {
        TreeMap<String, Object> config = new TreeMap<>();
        config.put("SecretId", secretId);
        config.put("SecretKey", secretKey);
        /* 请求方法类型 POST、GET */
        config.put("RequestMethod", requestMethod);
        /* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
        config.put("DefaultRegion", defaultRegion);
        return config;
    }


}
