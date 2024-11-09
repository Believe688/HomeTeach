package wgh.mediacore.domain;

import lombok.Data;

import java.util.Date;

/**
 * @TIME 2024/10/21
 * @USER Linn
 * 所有文件必须嵌套，且嵌套的深度不能超过2级，即A/B/C.png
 */
@Data
public class Medias {
    private String fileName;
    private String filePath;
    private String[] pathSegments;
    private int fileType;// 数据类型 1：图片 2：视频 3：阅读
    private Date fileCreateDate;
    private String fileOwner;
}
