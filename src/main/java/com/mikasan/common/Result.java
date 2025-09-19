package com.mikasan.common;

import lombok.Data;

@Data
public class Result<T> {

    /** 成功码 */
    public static final int DEFAULT_SUCCESS_CODE = 200;
    /** 通用失败码 */
    public static final int DEFAULT_ERROR_CODE   = 500;

    private int code;
    private String message;
    private T data;

    // 添加构造方法
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /* =============================================================
     * 快捷工厂方法 —— SUCCESS
     * ============================================================= */

    /** 成功：无数据、默认消息 */
    public static Result<Void> ok() {
        return new Result<>(DEFAULT_SUCCESS_CODE, "ok", null);
    }

    /** 成功：有数据、默认消息 */
    public static <T> Result<T> success(T data) {
        return new Result<>(DEFAULT_SUCCESS_CODE, "ok", data);
    }

    /** 成功：自定义消息、无数据 */
    public static Result<Void> success(String message) {
        return new Result<>(DEFAULT_SUCCESS_CODE, message, null);
    }

    /** 成功：自定义消息 + 数据 */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(DEFAULT_SUCCESS_CODE, message, data);
    }

    /* =============================================================
     * 快捷工厂方法 —— FAIL / ERROR
     * ============================================================= */

    /** 失败：默认错误码，消息 */
    public static <T> Result<T> fail(String message) {
        return new Result<>(DEFAULT_ERROR_CODE, message, null);
    }

    /** 失败：自定义错误码，消息 */
    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message, null);
    }

    /* 兼容旧版命名 */
    public static <T> Result<T> error(String message)            { return fail(message); }
    public static <T> Result<T> error(int code, String message)  { return fail(code, message); }
}