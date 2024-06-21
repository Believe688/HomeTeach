package yj.hometeach.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yj.hometeach.config.R;
import yj.hometeach.domain.schoolInfo;
import yj.hometeach.service.schoolService;

import java.util.List;

/**
 * @TIME 2024/6/18
 * @USER Linn
 */
@Slf4j
@RestController
@RequestMapping("/school")
public class schoolController {
    @Autowired
    private schoolService schoolService;

    @PostMapping("")
    public R getSchoolName(@RequestBody schoolNameArgs args) {
        try {
            List<schoolInfo> result = schoolService.getSchoolName(args.schoolArea, args.schoolProvince);
            if (result == null) {
                return new R(400, true);
            }
            return new R(200, true, result, result.size());
        } catch (Exception e) {
            log.error("Controller层错误=>{ }",e);
            return new R(500, false);
        }
    }

    @Data
    static class schoolNameArgs {
        String schoolArea;
        String schoolProvince;
    }
}
