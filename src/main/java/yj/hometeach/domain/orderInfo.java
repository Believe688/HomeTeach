package yj.hometeach.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.Pattern;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @TIME 2024/6/10
 * @USER Linn
 * 学生数据表
 */
@Document("studentInfo")
@Data
public class orderInfo {
    // 订单个人信息
    private String name;// 姓名
    private String sex;// 性别
    private String grade;// 年级
    private String phone;// 电话
    private String password;// 密码
    private String weChat;// weChat
    private String studyArea;// 当前所在地
    // order
    private String studyMajor; // 需帮助科目
    private String[] studyTypes; // 学员状态
    private String orderTeachType;// 要求教员类型
    private String orderSex; // 性别要求
    private String orderTime;// 教学时间
    private String orderTypes;// 学习方式
    private int orderNum; // 需求人数
    private String other; // 其他
    private String orderId;// 订单id,后期加
    private int isPublic;// 是否为可公开订单，后期加
    //Info
    private Date commitTime;// 发布时间，后期加
    private String[] teacherQueue;// 待选老师，后期加

}
