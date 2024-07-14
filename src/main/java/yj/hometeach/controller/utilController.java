package yj.hometeach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yj.hometeach.config.R;
import yj.hometeach.service.utilService;


/**
 * @TIME 2024/6/22
 * @USER Linn
 */
@RestController
@RequestMapping("/util")
public class utilController {
    @Autowired
    private utilService utilService;
    @PostMapping("/otp")
    public R sendOTP(String phone) {
//        if (utilService.sendOTP(phone)){
//            return new R(400,false);
//        };
        return new R(200, true);
    }
}
