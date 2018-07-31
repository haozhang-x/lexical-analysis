package cn.cloudx.namevoice;

import cn.cloudx.namevoice.config.TencentCloudConfig;
import cn.cloudx.namevoice.utils.JsonUtils;
import com.qcloud.Module.Wenzhi;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Utilities.Json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NameVoiceApplicationTests {

    @Autowired
    private TencentCloudConfig tencentCloudConfig;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testTencentCloudApi() {

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(), tencentCloudConfig.config());
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        /* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
        /* LexicalAnalysis 接口的部分可选参数如下 */
        params.put("text", "我爱洗澡");
        params.put("code", "2097152");
        params.put("type", "0");
        /*在这里指定所要用的签名算法，不指定默认为HmacSHA1*/
        //params.put("SignatureMethod", "HmacSHA256");
        /* generateUrl 方法生成请求串，但不发送请求。在正式请求中，可以删除下面这行代码。 */
        //System.out.println(module.generateUrl("LexicalAnalysis", params));

        String result = null;
        try {
            /* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
            result = module.call("LexicalAnalysis", params);
            JSONObject json_result = new JSONObject(result);
            System.out.println(json_result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }

    }
}
