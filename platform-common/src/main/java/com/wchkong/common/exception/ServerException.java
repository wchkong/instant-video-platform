package com.wchkong.common.exception;


import com.wchkong.common.enums.ServerResponseEnum;

/**
 * Created By Q.Hao
 * Description: 自定义的系统异常
 * Created At 2019/03/08
 */
public class ServerException extends RuntimeException {

    private Integer code;

    public ServerException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ServerException(ServerResponseEnum responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

    public ServerException(ServerResponseEnum responseCode, String message) {
        super(message);
        this.code = responseCode.getCode();
    }

    public ServerException(Exception e) {
        super(e.getMessage());
        this.code = ServerResponseEnum.SERVER_ERROR.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
