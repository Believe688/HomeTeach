package yj.hometeach.service;

import yj.hometeach.domain.teacherInfo;

import java.util.List;

/**
 * @DATE 2024/6/13
 * @USER Linn
 */
public interface teacherService {
    List getTeacherListByCondition(String[] teachArea, String graduateSchool, String[] teachMajor, String workTitle,
                                   String sex, int currentPage, int pageSize);
    List<teacherInfo> getTeacherInfoById(int teachId);
}
