package yj.hometeach.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yj.hometeach.config.R;
import yj.hometeach.service.utilService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @TIME 2024/6/22
 * @USER Linn
 */
@Service
@Slf4j
public class utilServiceImpl implements utilService {

    /**
     * 校验是否符合手机号规则，符合则发送OTP，返回true，否则返回false
     * @param phone 手机号
     * @return
     */
    @Override
    public Boolean sendOTP(String phone) {
        Pattern pattern = Pattern.compile("^1[3-9]\\d{9}$");
        if (pattern.matcher(phone).matches()){
            // send otp
            return true;
        }
        return false;
    }

    @Override
    public String generateId(String phone) {
        UUID generateId = UUID.nameUUIDFromBytes(phone.getBytes());
        String code = String.valueOf(generateId.hashCode());
        // 先返回8位的hashcode
        return code.substring(code.length()-8);
    }

    @Override
    public String getTime() {
        // new Data()获取当前时间
        // 格式化日期时间并返回字符串
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
