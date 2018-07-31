package cn.cloudx.namevoice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 分词配置
 * @author zhanghao
 * @date 2018/06/24
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "lexical-analysis-config")
public class LexicalAnalysisConfig {
    private String code;
    private Integer type;
}
