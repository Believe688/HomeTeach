package yj.hometeach.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yj.hometeach.config.R;
import yj.hometeach.domain.schoolAreaInfo;
import yj.hometeach.service.schoolAreaService;

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
    private schoolAreaService schoolService;
    @GetMapping("")
    public R getSchoolAreaList() {
        try {
            List<schoolAreaInfo> result = schoolService.getAreaList();
            if (result == null) {
                return new R(400, false );
            }
            return new R(200, true, result, result.size());
        } catch (Exception e) {
            log.error("Controller层错误=>{ }",e);
            return new R(500, false);
        }
    }

    @PostMapping("")
    public R getSchoolName(@RequestBody schoolNameArgs args) {
        try {
            List<schoolAreaInfo> result = schoolService.getSchoolName(args.schoolArea, args.schoolProvince);
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
    private static class schoolNameArgs {
        String schoolArea;
        String schoolProvince;
    }
}
