package wgh.mediacore.service;

import com.aliyuncs.exceptions.ClientException;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @DATE 2024/10/16
 * @USER Linn
 */
public interface GetFileService {
    URL getSignUrl(String objectName) throws ClientException;

    boolean notifyToAddFile(String path, String name);

    List getFileList(String owner) throws ClientException;

    Map getFileFolder();
}
