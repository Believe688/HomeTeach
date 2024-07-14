package yj.hometeach.service;

import yj.hometeach.domain.orderInfo;
import yj.hometeach.domain.teacherInfo;
import yj.hometeach.domain.userInfo;

/**
 * @DATE 2024/7/7
 * @USER Linn
 */
public interface registerService {
    int putTeacherInfo(userInfo teacherInfo);
    int putOrderInfo(orderInfo orderInfo);


}
