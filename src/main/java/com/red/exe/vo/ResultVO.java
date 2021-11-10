package com.red.exe.vo;

import lombok.Data;

/**
 * @author yanpeng.zhao
 * @date 2021/11/10 10:14
 */
@Data
public class ResultVO<T> {

    /**错误码*/
    private Integer code;
    /**提示信息*/
    private String msg;
    /**具体内容*/
    private T data;
}
