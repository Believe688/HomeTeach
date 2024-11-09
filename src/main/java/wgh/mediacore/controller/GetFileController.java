package wgh.mediacore.controller;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wgh.mediacore.config.R;
import wgh.mediacore.service.GetFileService;

import java.util.List;
import java.util.Map;

/**
 * @TIME 2024/10/19
 * @USER Linn
 */
@RestController
@RequestMapping("file")
public class GetFileController {
    @Autowired
    private GetFileService getFileService;

    @GetMapping
    public R getFileFolderPath() throws ClientException {
        Map folderList = getFileService.getFileFolder();
        return new R(true, folderList, folderList.size());
    }

    /**
     * 获取文件临时访问链接
     * @param objectName 文件名
     * @return 文件临时访问链接
     * @throws ClientException
     */
    @GetMapping("/sign")
    public R getFileSign(String objectName) throws ClientException {
        if (objectName == null || objectName.equals("")) {
            return new R(false);
        }
        if (getFileService.getSignUrl(objectName) == null) {
            return new R(false);
        }
        return new R(true, getFileService.getSignUrl(objectName),1);
    }

    @GetMapping("notify")
    public R getFileNotifyByWeb(String path, String name){
        boolean status = getFileService.notifyToAddFile(path, name);
        return new R(status);
    }
}
