package yj.hometeach.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import yj.hometeach.domain.orderInfo;
import yj.hometeach.domain.teacherInfo;
import yj.hometeach.service.orderService;

import java.util.List;
import yj.hometeach.dao.mongoDao;
/**
 * @TIME 2024/6/16
 * @USER Linn
 */
@Slf4j
@Service
public class orderServiceImpl implements orderService {
    @Autowired
    private mongoDao mongodao;

    @Override
    public List<orderInfo> getOrderListByCondition(String studyArea, String graduateSchool, String orderTeachType, String orderSex,
                                        int currentPage, int pageSize) {
        Query query = new Query();
        if (studyArea != null) {
            query.addCriteria(Criteria.where("studyArea").is(studyArea));
        }
        if (graduateSchool != null){
            query.addCriteria(Criteria.where("graduateSchool").is(graduateSchool));
        }
        if (orderTeachType != null) {
            query.addCriteria(Criteria.where("orderTeachType").is(orderTeachType));
        }
        if (orderSex != null){
            query.addCriteria(Criteria.where("orderSex").is(orderSex));
        }
        if (currentPage < 0) currentPage = 0;
        if (pageSize <= 0) pageSize = 10; // 假设默认页大小为10
        query.skip(currentPage).limit(pageSize);
        try {
            return mongodao.findByCondition(query, orderInfo.class);
        } catch (Exception e) {
            log.error("service层:{}", e.toString());
            throw new RuntimeException("查找订单出现问题，请重试");
        }
    }

    @Override
    public int putOrderInfo(orderInfo orderInfo) {
        try {

            return mongodao.insertDoc(orderInfo) ? 1 : 0;
        } catch (Exception e) {
            log.error("Put Order has error=>{ }",e);
            throw new RuntimeException("插入未成功，请重试");
        }
    }
}
