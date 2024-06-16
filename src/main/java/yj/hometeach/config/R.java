package yj.hometeach.config;

import lombok.Data;

/**
 * @TIME 2024/6/14
 * @USER Linn
 */
@Data
public class R {
    private boolean success;
    private Object data;
    private int total;
    /**
     * 状态码：
     * 200：成功
     * 400: 无报错但未成功
     * 403：权限错误
     * 404：用户端错误
     * 500：服务器端错误
     */
    private int code; // 状态码

    public R(int code, Boolean success, Object data, int total) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.total = total;
    }

    public R(int code, Boolean success, Object data) {
        this.success = success;
        this.data = data;
        this.code = code;
    }

    public R(int code, Boolean success) {
        this.success = success;
        this.code = code;
    }


}
