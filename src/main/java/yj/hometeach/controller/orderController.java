package yj.hometeach.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yj.hometeach.config.R;
import yj.hometeach.domain.orderInfo;
import yj.hometeach.service.orderService;
import yj.hometeach.service.utilService;

import java.util.ArrayList;
import java.util.List;

/**
 * @TIME 2024/6/16
 * @USER Linn
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class orderController {
    @Autowired
    private orderService orderService;
    @Autowired
    private utilService utilService;
    @GetMapping()
    public R getOrderList(String studyArea, String graduateSchool, String orderTeachType, String orderSex,
                          int currentPage, int pageSize){
        List result;
        try {
            result = orderService.getOrderListByCondition(studyArea, graduateSchool, orderTeachType, orderSex, currentPage, pageSize);
            if (result == null) {// 如果按照条件查询不到结果，则返回默认查询结果
                result = orderService.getOrderListByCondition(null, null, null, null, currentPage, pageSize);
                return new R(400,true, result, result.size());
            }
            return new R(200,true, result, result.size());
        } catch (Exception e) {
            log.error("Get order error=>{}", e.toString());
            return new R(500,false);
        }
    }

}
