package yj.hometeach.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.hometeach.dao.mongoDao;
import yj.hometeach.domain.orderInfo;
import yj.hometeach.domain.userInfo;
import yj.hometeach.service.registerService;
import yj.hometeach.service.utilService;

import java.util.UUID;

/**
 * @TIME 2024/7/7
 * @USER Linn
 */
@Slf4j
@Service
public class registerServiceImpl implements registerService {
    @Autowired
    private mongoDao mongodao;
    @Autowired
    private utilService utilService;

    @Override
    public int putTeacherInfo(userInfo userInfo) {
        try {
            return mongodao.insertDoc(userInfo) ? 1 : 0;
        } catch (Exception e) {
            log.error("Put Teacher has error=>{ }",e);
            throw new RuntimeException("插入未成功，请重试");
        }
    }

    @Override
    public int putOrderInfo(orderInfo orderInfo) {
        try {
            orderInfo.setOrderId("O"+utilService.generateId(7));// 生成订单ID
            orderInfo.setOrderTime(utilService.getTime());// 订单时间
            log.info("orderInfo=>{}", orderInfo);
            return mongodao.insertDoc(orderInfo) ? 1 : 0;
        } catch (Exception e) {
            log.error("Put Order has error=>{ }",e);
            throw new RuntimeException("插入未成功，请重试");
        }
    }
    private String putUserInfo(String phone, String password, String weChatCode) {
        return null;
    }
}
