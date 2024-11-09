package wgh.mediacore;

import com.aliyuncs.exceptions.ClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import wgh.mediacore.dao.MongoDao;
import wgh.mediacore.domain.Medias;
import wgh.mediacore.service.GetFileService;

import java.util.*;

@SpringBootTest
class MediaCoreApplicationTests {
    @Autowired
    private GetFileService getFileService;
    @Autowired
    private MongoDao mongoDao;

    @Test
    void contextLoads() throws ClientException {

        List fileList = getFileService.getFileList("");
        for (Object o : fileList) {
            System.out.println(o);
        }

    }
    @Test
    void testUtil(){
        String text = "https://media.haolin10.cn/emptyImg.png";
        System.out.println(text.replace("https://media.haolin10.cn/", ""));
    }

    @Test
    void testPath(){
        System.out.println(getFileService.getFileFolder());
    }
}
