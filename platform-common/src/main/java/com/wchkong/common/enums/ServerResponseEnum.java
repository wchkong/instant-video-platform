package com.wchkong.common.enums;

/**
 * Created By Q.Hao
 * Description: 服务端响应码
 * Created At 2019/03/08
 */
public enum ServerResponseEnum {
    ILLEGAL_SIGN(9, "签名不合法"),
    FAIL(10, "请求失败"),

    ACCOUNT_NOT_EXIST(11, "账号不存在"),
    DUPLICATE_ACCOUNT(12, "账号或邮箱重复"),
    ACCOUNT_IS_DISABLED(13, "账号被禁用"),
    INCORRECT_CREDENTIALS(14, "账号或密码错误"),
    NOT_LOGGED_IN(15, "请登录系统"),
    WRONG_PASSWORD(16, "旧密码错误"),

    INSERT_FAILED(17, "新增失败"),
    UPDATE_FAILED(18, "更新失败"),
    DELETE_FAILED(19, "删除失败"),

    REMOTE_CALL_FAIL(20, "远程调用失败"),
    INSTITUTION_NOT_EXIST(21, "机构不存在"),
    USER_INFO_NOT_EXIST(22, "用户信息不存在"),
    NOT_EDITABLE(23, "不可修改"),
    PASSWORD_FORMAT_INVALID(24, "密码长度或格式非法"),
    BUSINESS_NOT_EXIST(25, "该业务不存在"),
    DUPLICATE_PROJECT_NAME(26, "该项目名称已存在"),
    PARAM_IS_EMPTY(27, "必填参数为空或用户信息为空"),
    RESULT_SET_IS_EMPTY(28, "结果集为空或数量有误"),
    BASE_XML_NOT_FOUND(29, "base xml不存在！"),
    WORK_ORDER_NOT_PASSED(30, "工单审核不通过"),
    //WORK_ORDER_NOT_PASSED(31, "工单审核不通过"),
    POD_IS_EMPTY(31, "deployment没有pod存在"),
    ACCOUNT_RETRY_LIMIT(32, "账号或密码错误多次被禁用"),
    CHECK_VERIFY_FAIL(33, "验证码校验失败"),
    PASSWORD_TOO_SIMPLE(34, "密码强度不够，请重新输入"),
    ONE_CRID_ONLY_PACKING_ONCE(35, "一次代码审查记录只能打包一次！"),
    DOMAIN_ROLE_DUPLICATE(36, "域名角色重复"),
    DOWNLOAD_FILE_ERROR(37, "远程下载文件失败"),


    SUCCESS(200, "请求成功"),
    UNAUTHORIZED(403, "权限不足"),
    SERVER_ERROR(500, "服务器内部错误"),
    ;

    Integer code;

    String message;

    ServerResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
