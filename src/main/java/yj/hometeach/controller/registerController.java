package yj.hometeach.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yj.hometeach.config.R;
import yj.hometeach.domain.orderInfo;
import yj.hometeach.domain.userInfo;
import yj.hometeach.service.registerService;

/**
 * @TIME 2024/7/7
 * @USER Linn
 */
@Slf4j
@RestController
@RequestMapping("/res")
public class registerController {
    @Autowired
    private registerService registerService;
    @PostMapping("/addO")
    public R putOrderInfo(@RequestBody orderInfo orderInfo){
        int result = 0;
        try {
            log.info(orderInfo.toString());
            result = registerService.putOrderInfo(orderInfo);
        } catch (Exception e) {
            log.error("Put order error=>{}", e.toString());
            return new R(500,false);
        }
        if (result == 0) {
            return new R(400,false);
        }
        return new R(200,true);
    }
    /**
     * 添加教师，需权限
     * @param userInfo 教师信息
     * @return
     */
    @PostMapping("/addT")
    public R putTeacherInfo(@RequestBody userInfo userInfo) {
        try {
            if (registerService.putTeacherInfo(userInfo) == 0) {
                return new R(400,false);
            }
            return new R(200,true);
        } catch (Exception e) {
            log.error("Add Teacher has error=>{}",e.getMessage());
            return new R(500,false);
        }
    }
}
