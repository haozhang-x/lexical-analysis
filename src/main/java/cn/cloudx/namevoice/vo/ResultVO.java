package cn.cloudx.namevoice.vo;

import cn.cloudx.namevoice.domain.Word;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 最外层返回的VO
 * @author zhanghao
 * @date 2018/06/24
 */
@Data
public class ResultVO implements Serializable {

    private static final long serialVersionUID = 3840475480155327100L;
    private Integer code;
    private String message;
    private List<Word> wordList;
}
