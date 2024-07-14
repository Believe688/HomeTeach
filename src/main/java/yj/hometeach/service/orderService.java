package yj.hometeach.service;

import yj.hometeach.domain.orderInfo;
import yj.hometeach.domain.teacherInfo;

import java.util.List;

/**
 * @DATE 2024/6/16
 * @USER Linn
 */
public interface orderService {
    List getOrderListByCondition(String studyArea, String graduateSchool, String orderTeachType, String orderSex,
                                 int currentPage, int pageSize);

}
