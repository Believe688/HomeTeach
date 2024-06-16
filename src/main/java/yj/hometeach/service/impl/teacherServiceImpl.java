package yj.hometeach.service.impl;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import yj.hometeach.domain.teacherInfo;
import yj.hometeach.service.teacherService;
import yj.hometeach.dao.mongoDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @TIME 2024/6/13
 * @USER Linn
 */
@Slf4j
@Service
public class teacherServiceImpl implements teacherService {

    @Autowired
    private mongoDao mongodao;

    @Override
    public List getTeacherListByCondition(String[] teachArea, String graduateSchool, String[] teachMajor, String workTitle,
                                          String sex, int currentPage, int pageSize) {
        Query query = new Query();
        if (teachArea.length > 0) {
            query.addCriteria(Criteria.where("teachArea").all(teachArea));
        }
        if (graduateSchool != null){
            query.addCriteria(Criteria.where("graduateSchool").is(graduateSchool));
        }
        if (teachMajor.length > 0) {
            query.addCriteria(Criteria.where("teachMajor").all(teachMajor));
        }
        if (workTitle != null){
            query.addCriteria(Criteria.where("workTitle").is(workTitle));
        }
        if (sex != null){
            query.addCriteria(Criteria.where("sex").is(sex));
        }
        if (currentPage < 0) currentPage = 0;
        if (pageSize <= 0) pageSize = 10; // 假设默认页大小为10
        query.skip(currentPage).limit(pageSize);

        try {
            return mongodao.findByCondition(query, teacherInfo.class);
        } catch (Exception e) {
            log.error("service层按条件查询时出错:{}", e.toString());
            throw new RuntimeException("查找出现问题，请重试");
        }
    }

    @Override
    public List<teacherInfo> getTeacherInfoById(int teachId){
        try {
            return mongodao.findByCondition(new Query(Criteria.where("teachId").is(teachId)), teacherInfo.class);
        }catch (Exception e){
            log.error("service层查询ID时出错:{}", e.toString());
            throw new RuntimeException("查找出现问题，请重试");
        }
    }

    @Override
    public int putTeacherInfo(teacherInfo teacherInfo){
        try {
            // 通过手机号作为唯一键来生成UUID，后期改为身份证号码
            teacherInfo.setTeachId(UUID.nameUUIDFromBytes(teacherInfo.getPhone().getBytes()).version());
            return mongodao.insertDoc(teacherInfo) ? 1 : 0;
        } catch (Exception e) {
            log.error("Put Teacher has error=>{ }",e);
            throw new RuntimeException("插入未成功，请重试");
        }
    }
}
