package yj.hometeach.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import yj.hometeach.dao.mongoDao;
import yj.hometeach.domain.schoolInfo;
import yj.hometeach.service.schoolService;

import java.util.List;

/**
 * @TIME 2024/6/18
 * @USER Linn
 */
@Slf4j
@Service
public class schoolServiceImpl implements schoolService {

    @Autowired
    private mongoDao mongoDao;
    @Override
    public List<schoolInfo> getSchoolName(String schoolArea, String schoolProvince) {
        Query query = new Query();
        if (schoolArea != null) {
            query.addCriteria(Criteria.where("schoolArea").is(schoolArea));
        }
        if (schoolProvince != null) {
            query.addCriteria(Criteria.where("schoolProvince").is(schoolProvince));
        }
        List<schoolInfo> list = null;
        try {
            return mongoDao.findByCondition(query, schoolInfo.class);
        } catch (Exception e) {
            log.error("查询地区失败=>{ }", e);
            throw new RuntimeException("查询失败，请重试");
        }
    }
}
