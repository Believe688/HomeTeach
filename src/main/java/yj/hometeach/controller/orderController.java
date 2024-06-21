package yj.hometeach.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yj.hometeach.config.R;
import yj.hometeach.domain.orderInfo;
import yj.hometeach.service.orderService;

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
    @GetMapping()
    public R getOrderList(String studyArea, String graduateSchool, String orderTeachType, String orderSex,
                          int currentPage, int pageSize){
        List result;
        try {
            result = orderService.getOrderListByCondition(studyArea, graduateSchool, orderTeachType, orderSex, currentPage, pageSize);
        } catch (Exception e) {
            log.error("Get order error=>{}", e.toString());
            return new R(500,false);
        }
        if (result == null) {
            return new R(400,false);
        }
        return new R(200,true, result, result.size());
    }

    @PostMapping("/add")
    public R putOrderInfo(@RequestBody orderInfo orderInfo){
        int result = 0;
        try {
            result = orderService.putOrderInfo(orderInfo);
        } catch (Exception e) {
            log.error("Put order error=>{}", e.toString());
            return new R(500,false);
        }
        if (result == 0) {
            return new R(400,false);
        }
        return new R(200,true);
    }
}
