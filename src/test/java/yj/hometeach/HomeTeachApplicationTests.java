package yj.hometeach;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import yj.hometeach.dao.mongoDao;
import yj.hometeach.domain.schoolAreaInfo;
import yj.hometeach.domain.teacherInfo;
import yj.hometeach.service.schoolAreaService;
import yj.hometeach.service.teacherService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Random;

@Slf4j
@SpringBootTest
class HomeTeachApplicationTests {
    @Autowired
    private mongoDao mongodao;
    @Autowired
    private teacherService teacherService;
    @Autowired
    private schoolAreaService schoolAreaService;

    @Test
    void mockTeacher() {
        List<teacherInfo> teachers = generateTestData(50);
        for (teacherInfo teacher : teachers) {
            mongodao.insertDoc(teacher);
            System.out.println(teacher);
        }

    }
    private static List<teacherInfo> generateTestData(int count) {
        List<teacherInfo> teachers = new ArrayList<>();
        Random random = new Random();
        String[] graduateSchools = {"北京大学", "清华大学", "复旦大学", "上海交通大学", "浙江大学"};
        String[] educations = {"本科", "硕士", "博士"};
        String[] majors = {"计算机科学", "数学", "物理", "化学", "生物"};
        String[] secondLanguages = {"英语", "日语", "法语", "德语"};
        String[] languageLevels = {"CET-4", "CET-6", "JLPT-N1", "DELF-B2"};
        String[] credentials = {"高级教师证书", "计算机等级证书", "TESOL证书"};
        String[] workExperiences = {"5年", "10年", "15年", "20年"};
        String[] teachTypes = {"一对一教学", "小班授课", "大班授课"};
        String[] workTitles = {"在职教师", "研究生", "本科", "博士", "专科"};
        String[] teachAreas = {"北京", "上海", "广州", "深圳", "杭州"};

        for (int i = 0; i < count; i++) {
            teacherInfo teacher = new teacherInfo();
            teacher.setName("教师" + (i + 1));
            teacher.setPhone("13800138" + random.nextInt(10000));
            teacher.setWeChat("teacher_wechat_" + (i + 1));
            teacher.setBornTime(new Date(System.currentTimeMillis() - random.nextInt((int) 10000000000L)));
            teacher.setSex(random.nextBoolean() ? "男" : "女");
            teacher.setCurrentPlace(teachAreas[random.nextInt(teachAreas.length)]);
            teacher.setSelfIntroduction("资深教师，多年教学经验。");
            teacher.setSelfExperience("曾在多所知名学校任教。");
            teacher.setTeachId(1001 + i);

            teacher.setGraduateSchool(graduateSchools[random.nextInt(graduateSchools.length)]);
            teacher.setEducation(educations[random.nextInt(educations.length)]);
            teacher.setMajor(majors[random.nextInt(majors.length)]);
            teacher.setSecondLanguage(new String[]{secondLanguages[random.nextInt(secondLanguages.length)]});
            teacher.setLanguageLevel(new String[]{languageLevels[random.nextInt(languageLevels.length)]});
            teacher.setCredentials(new String[]{credentials[random.nextInt(credentials.length)]});

            teacher.setWorkExperience(workExperiences[random.nextInt(workExperiences.length)]);
            teacher.setTeachType(teachTypes[random.nextInt(teachTypes.length)]);
            teacher.setTeachMajor(new String[]{majors[random.nextInt(majors.length)]});
            teacher.setWorkTime("全职");
            teacher.setWorkTitle(workTitles[random.nextInt(workTitles.length)]);
            teacher.setTeachArea(new String[]{teachAreas[random.nextInt(teachAreas.length)]});
            teacher.setIsPublic(random.nextInt(2));

            teacher.setCreateTime(new Date());
            teacher.setApplyOrder(new String[]{"ORDER" + (i + 1001), "ORDER" + (i + 1002)});
            teacher.setSuccessOrder(new String[]{"ORDER" + (i + 1001)});

            teachers.add(teacher);
        }

        return teachers;
    }

    @Test
    void testFindOneDoc() {
        List<schoolAreaInfo> areaList = schoolAreaService.getAreaList();
        for (schoolAreaInfo schoolAreaInfo : areaList) {
            System.out.println(schoolAreaInfo);
        }

    }
}
