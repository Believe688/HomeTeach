package yj.hometeach.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @TIME 2024/7/7
 * @USER Linn
 * 注册用户信息，作为母表做校验使用
 */
@Document("userInfo")
@Data
public class userInfo {
    String phone; // 手机号
    String password; // 密码
    String weChatCode; // 微信返回的唯一Code
}
