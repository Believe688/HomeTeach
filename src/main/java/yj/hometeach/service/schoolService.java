package yj.hometeach.service;

import yj.hometeach.domain.schoolInfo;

import java.util.List;

/**
 * @DATE 2024/6/18
 * @USER Linn
 */
public interface schoolService {
    List<schoolInfo> getSchoolName(String schoolArea, String schoolProvince);
}
