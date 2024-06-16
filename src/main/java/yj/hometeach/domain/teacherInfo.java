package yj.hometeach.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @TIME 2024/6/10
 * @USER Linn
 */
@Document("teachInfo")
@Data
public class teacherInfo {
    /**
     * 更新信息：
     * graduateSchool 更改为 String[]
     * workTitle 改为：在职教师、研究生、本科、博士、专科
     *
     */
    // 基础信息
    private String name;// 名字
    private String phone;// 电话
    private String weChat;// 微信
    private Date bornTime;// 出生日期
    private String sex;// 性别
    private String currentPlace;// 现居住地
    private String selfIntroduction;// 自我介绍
    private String selfExperience;// 自我经历
    private int teachId;// 教师ID
    // 学历信息
    private String graduateSchool;// 毕业院校
    private String education;// 学历
    private String major;// 专业
    private String[] secondLanguage;// 外语
    private String[] languageLevel;// 外语水平
    private String[] credentials;// 资质证明
    // 教学信息
    private String workExperience;// 教学经验
    private String teachType;// 教学类型
    private String[] teachMajor;// 教学专业
    private String workTime;// 教学时间
    private String workTitle;// 现身份
    private String[] teachArea;// 教学区域
    private int isPublic;// 是否公开
    // Info
    private Date createTime;// 注册时间
    private String[] applyOrder;// 申请订单
    private String[] successOrder;// 成功订单
}
