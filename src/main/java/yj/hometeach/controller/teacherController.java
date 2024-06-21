package yj.hometeach.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yj.hometeach.config.R;
import yj.hometeach.domain.teacherInfo;
import yj.hometeach.service.teacherService;

import java.util.List;

/**
 * @TIME 2024/6/14
 * @USER Linn
 */
@Slf4j
@RestController
@RequestMapping("/teach")
public class teacherController {
    @Autowired
    private teacherService teacherService;

    /**
     * 搜索教师
     * @param teachArea 教学区域
     * @param graduateSchool 毕业院校
     * @param teachMajor 教学专业
     * @param workTitle 职称
     * @param sex 性别
     * @param currentPage 当前页码
     * @param pageSize 页大小
     * @return
     */
    @GetMapping("/normal")
    public R getTeacherList(String[] teachArea, String graduateSchool, String[] teachMajor, String workTitle,
                            String sex, int currentPage, int pageSize) {
        List result = null;
        if (teachArea == null){
            teachArea = new String[]{};
        }
        if (teachMajor == null){
            teachMajor = new String[]{};
        }
        try {
            result = teacherService.getTeacherListByCondition(teachArea, graduateSchool, teachMajor, workTitle, sex,
                    currentPage, pageSize);
        } catch (Exception e) {
            log.error("controller层提示=>{}",e.getMessage());
            return new R(500,false);
        }
        if (result == null) {
            return new R(400,false);
        }
        return new R(200,true, result, result.size());
    }

    @PostMapping("search")
    public R getTeacherInfoById(@RequestBody int teachId) {
        List result = null;
        try {
            result = teacherService.getTeacherInfoById(teachId);
        } catch (Exception e) {
            log.error("Controller层错误=>{}",e.toString());
            return new R(500,false);
        }
        return new R(200,true, result, result.size());
    }
    /**
     * 添加教师，需权限
     * @param teacherInfo 教师信息
     * @return
     */
    @PostMapping("/add")
    public R putTeacherInfo(teacherInfo teacherInfo) {
        int result = 0;
        try {
            result = teacherService.putTeacherInfo(teacherInfo);
        } catch (Exception e) {
            log.error("Add Teacher has error=>{}",e.getMessage());
            return new R(500,false);
        }
        if (result == 0) {
            return new R(400,false);
        }
        return new R(200,true);
    }

}
