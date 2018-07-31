package cn.cloudx.namevoice.controller;

import cn.cloudx.namevoice.service.LexicalAnalysisService;
import cn.cloudx.namevoice.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 展示分词后的结果
 *
 * @author zhanghao
 * @date 2018/06/24
 */
@RestController
public class IndexController {

    private final LexicalAnalysisService lexicalAnalysisService;

    @Autowired
    public IndexController(LexicalAnalysisService lexicalAnalysisService) {
        this.lexicalAnalysisService = lexicalAnalysisService;
    }

    @GetMapping("/")
    @Cacheable(cacheNames = "resultVO", key = "#text")
    public ResultVO index(String text) {
        return lexicalAnalysisService.resultVO(lexicalAnalysisService.lexicalAnalysis(text));
    }
}
