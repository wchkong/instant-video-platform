package com.wchkong.common;

import com.wchkong.common.enums.ServerResponseEnum;
import com.wchkong.common.exception.ServerException;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: kongweichang
 * @Date: 2019/5/15 11:31
 */
@Data
@ToString
public class ServerResponse<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public ServerResponse() {
    }

    public ServerResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ServerResponse(ServerResponseEnum responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    private ServerResponse(ServerResponseEnum responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    private ServerResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ServerResponse success() {
        return new ServerResponse(200, "请求成功", null);
    }

    public static ServerResponse fail() {
        return new ServerResponse(400, "请求失败", null);
    }

    public static ServerResponse success(Object data) {
        return new ServerResponse(200, "请求成功", data);
    }

    /**
     * 返回错误信息
     *
     * @return
     */
    public static ServerResponse error() {
        return new ServerResponse(ServerResponseEnum.SERVER_ERROR);
    }

    /**
     * 返回错误信息
     *
     * @param responseCode 响应码
     * @return
     */
    public static ServerResponse error(ServerResponseEnum responseCode) {
        return new ServerResponse(responseCode);
    }

    /**
     * 返回错误信息
     *
     * @param e 异常信息
     * @return
     */
    public static ServerResponse error(ServerException e) {
        return new ServerResponse(e.getCode(), e.getMessage());
    }

    public static ServerResponse error(ServerResponseEnum e, String message) {
        return new ServerResponse(e.getCode(), message);
    }
}

