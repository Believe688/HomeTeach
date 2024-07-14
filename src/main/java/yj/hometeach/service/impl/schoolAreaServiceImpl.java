package yj.hometeach.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import yj.hometeach.dao.mongoDao;
import yj.hometeach.domain.schoolAreaInfo;
import yj.hometeach.service.schoolAreaService;

import java.util.List;

/**
 * @TIME 2024/6/18
 * @USER Linn
 */
@Slf4j
@Service
public class schoolAreaServiceImpl implements schoolAreaService {

    @Autowired
    private mongoDao mongoDao;
    @Override
    public List<schoolAreaInfo> getSchoolName(String schoolArea, String schoolProvince) {
        Query query = new Query();
        if (!schoolArea.isEmpty()) {
            query.addCriteria(Criteria.where("schoolArea").is(schoolArea));
        }
        if (!schoolProvince.isEmpty()) {
            query.addCriteria(Criteria.where("schoolProvince").is(schoolProvince));
        }
        List<schoolAreaInfo> list = null;
        try {
            return mongoDao.findByCondition(query, schoolAreaInfo.class);
        } catch (Exception e) {
            log.error("查询地区失败=>{ }", e);
            throw new RuntimeException("查询失败，请重试");
        }
    }

    @Override
    public List<schoolAreaInfo> getAreaList() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("schoolProvince").addToSet("schoolArea").as("schoolArea")
        );
        return mongoDao.getColumns(agg, schoolAreaInfo.class);
    }
}
