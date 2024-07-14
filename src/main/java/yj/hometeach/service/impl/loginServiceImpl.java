package yj.hometeach.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import yj.hometeach.dao.mongoDao;
import yj.hometeach.domain.orderInfo;
import yj.hometeach.service.loginService;
import yj.hometeach.service.utilService;

import java.util.List;

/**
 * @TIME 2024/6/22
 * @USER Linn
 */
@Slf4j
@Service
public class loginServiceImpl implements loginService {
    @Autowired
    private utilService utilService;
    @Autowired
    private mongoDao mongoDao;
    @Override
    public String login(@NonNull String phone, String password, String code) {
        if (password == null && code == null) {// 发送验证码
            return utilService.sendOTP(phone);
        }
        if (password != null && code == null){// 验证密码
            Query query = new Query(Criteria.where("phone").is(phone));
            List<orderInfo> result = mongoDao.findByCondition(query, orderInfo.class);
            if (result.isEmpty()) {
                return null;
            }
            if (result.get(0).getPassword().equals(password))
                return "success";
        }
        return null;
    }
}
