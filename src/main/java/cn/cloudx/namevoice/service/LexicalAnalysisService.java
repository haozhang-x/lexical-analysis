package cn.cloudx.namevoice.service;

import cn.cloudx.namevoice.config.LexicalAnalysisConfig;
import cn.cloudx.namevoice.config.TencentCloudConfig;
import cn.cloudx.namevoice.domain.Dictionary;
import cn.cloudx.namevoice.domain.Word;
import cn.cloudx.namevoice.utils.PinyinUtils;
import cn.cloudx.namevoice.vo.ResultVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.qcloud.Module.Wenzhi;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Utilities.Json.JSONArray;
import com.qcloud.Utilities.Json.JSONObject;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 分词Service
 *
 * @author zhanghao
 * @date 2018/06/24
 */
@Service
public class LexicalAnalysisService {
    private final TencentCloudConfig tencentCloudConfig;
    private final LexicalAnalysisConfig lexicalAnalysisConfig;

    @Autowired
    public LexicalAnalysisService(TencentCloudConfig tencentCloudConfig, LexicalAnalysisConfig lexicalAnalysisConfig) {
        this.tencentCloudConfig = tencentCloudConfig;
        this.lexicalAnalysisConfig = lexicalAnalysisConfig;
    }

    public JSONObject lexicalAnalysis(String text) {
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(), tencentCloudConfig.config());
        TreeMap<String, Object> params = new TreeMap<>();
        /* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
        /* LexicalAnalysis 接口的部分可选参数如下 */
        params.put("text", text);
        params.put("code", lexicalAnalysisConfig.getCode());
        params.put("type", lexicalAnalysisConfig.getType());
        String result;
        JSONObject jsonResult = null;
        try {
            /* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
            result = module.call("LexicalAnalysis", params);
            jsonResult = new JSONObject(result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }

        return jsonResult;
    }

    /**
     * 解析Json对象为ResultVO
     *
     * @param jsonObject json对象
     * @return ResultVO
     */

    public ResultVO resultVO(JSONObject jsonObject) {
        ResultVO resultVO = new ResultVO();
        JSONArray tokens = jsonObject.getJSONArray("tokens");
        int code = jsonObject.getInt("code");
        String message = jsonObject.getString("message");
        resultVO.setCode(code);
        resultVO.setMessage(message);
        List<Word> wordList = new ArrayList<>();
        for (int i = 0; i < tokens.length(); i++) {
            JSONObject json = tokens.getJSONObject(i);
            String wordStr = json.getString("word");
            int pos = json.getInt("pos");
            String wLen = json.getString("wlen");
            int wTypePos = json.getInt("wtype_pos");
            String wType = json.getString("wtype");
            String pinyin = null;
            try {
                pinyin = PinyinUtils.toPinYin(wordStr);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }

            RestTemplate restTemplate = new RestTemplate();
            String dictionaryJsonArraySrc = restTemplate.getForObject("https://www.pwxcoo.com/dictionary?type=word&word=".concat(wordStr), String.class);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            JsonArray dictionaryJsonArray = gson.fromJson(dictionaryJsonArraySrc, JsonArray.class);
            Word word = new Word();
            word.setWord(wordStr);
            word.setPos(pos);
            word.setWLen(wLen);
            word.setWType(wType);
            word.setWTypePos(wTypePos);
            word.setPinyin(pinyin);
            wordList.add(word);
            if (dictionaryJsonArray.size() > 0) {
                Dictionary dictionary = new Dictionary();
                dictionary.setWord(dictionaryJsonArray.get(0).getAsJsonObject().get("word").getAsString());
                dictionary.setOldword(dictionaryJsonArray.get(0).getAsJsonObject().get("oldword").getAsString());
                dictionary.setPinyin(dictionaryJsonArray.get(0).getAsJsonObject().get("pinyin").getAsString());
                dictionary.setStrokes(dictionaryJsonArray.get(0).getAsJsonObject().get("strokes").getAsInt());
                dictionary.setRadicals(dictionaryJsonArray.get(0).getAsJsonObject().get("radicals").getAsString());
                dictionary.setExplanation(dictionaryJsonArray.get(0).getAsJsonObject().get("explanation").getAsString());
                word.setDictionary(dictionary);
            }


        }
        resultVO.setWordList(wordList);
        return resultVO;
    }


}
