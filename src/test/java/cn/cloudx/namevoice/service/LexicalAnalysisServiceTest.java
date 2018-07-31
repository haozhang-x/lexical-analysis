package cn.cloudx.namevoice.service;

import com.qcloud.Utilities.Json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanghao
 * @date 2018/06/24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LexicalAnalysisServiceTest {

    @Autowired
    private LexicalAnalysisService lexicalAnalysisService;

    @Test
    public void lexicalAnalysis() {
        JSONObject jsonObject= lexicalAnalysisService.lexicalAnalysis("我爱洗澡");
        log.info("returnResult={}", jsonObject);
        Assert.assertNotNull(jsonObject);
    }
}