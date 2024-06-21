package yj.hometeach;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import yj.hometeach.dao.mongoDao;
import yj.hometeach.domain.teacherInfo;
import yj.hometeach.service.teacherService;

import java.util.List;

@Slf4j
@SpringBootTest
class HomeTeachApplicationTests {
    @Autowired
    private mongoDao mongodao;
    @Autowired
    private teacherService teacherService;

    @Test
    void contextLoads() {
        teacherInfo teacherInfo = new teacherInfo();
        teacherInfo.setName("王五");
        teacherInfo.setPhone("13053767825");
        teacherInfo.setSex("男");
        teacherInfo.setEducation("本科");
        teacherInfo.setWorkExperience("5年");
        teacherInfo.setWorkTitle("教师");
        teacherInfo.setSelfExperience("5年");
        teacherInfo.setGraduateSchool("青岛农业大学");
        teacherInfo.setMajor("计算机科学与技术");
        teacherInfo.setTeachType("全职");
        teacherInfo.setTeachArea(new String[]{"北京", "南京"});
        teacherInfo.setTeachMajor(new String[]{"计算机科学与技术", "软件工程"});
        teacherInfo.setLanguageLevel(new String[]{"英语四级"});
        teacherInfo.setSecondLanguage(new String[]{"英语"});
        teacherInfo.setCredentials(new String[]{"教师资格证"});
        teacherInfo.setWorkTime("9:00-18:00");
        teacherInfo.setCurrentPlace("北京");
        teacherInfo.setSelfIntroduction("本人是计算机科学与技术专业毕业，5年教师经验，5年教学经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，5年教学管理经验，");
        teacherInfo.setTeachId(00000001);
        mongodao.insertDoc(teacherInfo);
    }

    @Test
    void testFindOneDoc() {
        List<teacherInfo> list = teacherService.getTeacherListByCondition(new String[]{}, null, new String[]{}, null, null,
                0, 10);
        for (teacherInfo teacherInfo : list) {
            System.out.println(JSONObject.toJSONString(list));
        }
        System.out.println(list);
//        for (Object o : testList) {
//            System.out.println(o);
//        }
    }
}
