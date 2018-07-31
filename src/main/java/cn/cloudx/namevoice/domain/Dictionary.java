package cn.cloudx.namevoice.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典类
 *
 * @author zhanghao
 * @date 2018/06/24
 */
@Data
public class Dictionary implements Serializable {
    private static final long serialVersionUID = 1921996441027840244L;
    private String word;
    private String oldword;
    private Integer strokes;
    private String pinyin;
    private String radicals;
    private String explanation;
}
