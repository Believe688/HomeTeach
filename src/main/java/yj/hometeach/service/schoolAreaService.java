package yj.hometeach.service;

import yj.hometeach.domain.schoolAreaInfo;

import java.util.List;

/**
 * @DATE 2024/6/18
 * @USER Linn
 */
public interface schoolAreaService {
    List<schoolAreaInfo> getSchoolName(String schoolArea, String schoolProvince);
    List<schoolAreaInfo> getAreaList();
}
