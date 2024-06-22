package yj.hometeach.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yj.hometeach.config.R;

/**
 * @TIME 2024/6/22
 * @USER Linn
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class loginController {
    @PostMapping
    public R login(String phone, String password) {
        return new R(200, true);
    }
}
