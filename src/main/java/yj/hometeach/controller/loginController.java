package yj.hometeach.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yj.hometeach.config.R;
import yj.hometeach.service.loginService;

/**
 * @TIME 2024/6/22
 * @USER Linn
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class loginController {
    @Autowired
    private loginService loginService;
    @PostMapping()
    public R login(@RequestBody LoginArgs args) {
        String result = loginService.login(args.phone, args.password, args.code);
        if (result == null) {
            return new R(400, false);
        }
        return new R(200, true, result);
    }
    @Data
    private static class LoginArgs {
        String phone;
        String password;
        String code;
    }
}

