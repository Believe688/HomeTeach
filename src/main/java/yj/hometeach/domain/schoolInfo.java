package yj.hometeach.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @TIME 2024/6/17
 * @USER Linn
 */
@Document("school")
@Data
public class schoolInfo {
    private String schoolCode;
    private Boolean isGov;
    private String schoolName;
    private String schoolLead;
    private String schoolArea;
    private String schoolGrade;
    private String schoolProvince;
}
