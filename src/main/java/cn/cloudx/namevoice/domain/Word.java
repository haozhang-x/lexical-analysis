package cn.cloudx.namevoice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 词
 *
 * @author zhanghao
 * @date 2018/06/24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Word implements Serializable {
    private static final long serialVersionUID = -3361754611216114052L;
    /**
     * 切分出来的基础词
     */
    private String word;
    /**
     * 该基础词在文本中的起始位置，单位为字节数
     */
    private int pos;
    /**
     * 基础词的词性，详细内容参照词性对照表
     */
    private String wType;
    /**
     * 基础词的词性的 ID，与“wtype ”对应，同参照文末词性对照表
     */
    private int wTypePos;
    /**
     * 该基础词的长度，单位为字节数，如：“我”为 2个字节，该值为 2
     */
    private String wLen;


    /**
     * 该词的拼音
     */
    private String pinyin;

    private Dictionary dictionary;

}
