package wgh.mediacore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import wgh.mediacore.dao.MongoDao;
import wgh.mediacore.domain.Medias;
import wgh.mediacore.service.GetFileService;

import java.net.URL;
import java.util.*;

/**
 * @TIME 2024/10/16
 * @USER Linn
 */
@Service
@Slf4j
public class GetFileServiceImpl implements GetFileService {

    @Autowired
    private MongoDao mongoDao;

    private final String bucketName = "mediacore";

    private OSS getInfo() throws ClientException {
        String endpoint = "https://media.haolin10.cn";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setSupportCname(true);
        // 创建OSSClient实例。
        return new OSSClientBuilder().build(endpoint, credentialsProvider, conf);
    }

    @Override
    public URL getSignUrl(String objectName) throws ClientException {
        OSS ossClient = getInfo();
        // 设置过期时间为60分钟：3600。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        try {
            // 设置生成预签名URL的请求。
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName,
                    HttpMethod.GET);
            // 设置过期时间。
            request.setExpiration(expiration);
            // 通过HTTP GET请求生成签名URL。
            return ossClient.generatePresignedUrl(request);
        } catch (com.aliyun.oss.ClientException e) {
            log.error("Error Message:{}", e.getMessage());
            return null;
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     * 接受上传回调，并将文件信息写入数据库
     * @param path 路径
     * @param name 文件名
     * @return
     */
    @Override
    public boolean notifyToAddFile(String path, String name){
        String folderPath = path.replace(name, "").replaceFirst("/", "");

        log.info("接收到文件路径：{}", folderPath);
        Medias medias = new Medias();
        medias.setFilePath(folderPath);
        medias.setPathSegments(folderPath.split("/"));
        medias.setFileName(name);
        medias.setFileCreateDate(new Date());
        medias.setFileType(getFileType(name));
        return mongoDao.insertDoc(medias);
    }

    private static Map<String, Integer> fileTypes = new HashMap<>();

    static {
        fileTypes.put("jpg", 1);
        fileTypes.put("jpeg", 1);
        fileTypes.put("png", 1);
        fileTypes.put("gif", 1);
        // 更多图片格式...

        fileTypes.put("mp4", 2);
        fileTypes.put("avi", 2);
        fileTypes.put("mov", 2);
        fileTypes.put("wmv", 2);
        // 更多视频格式...

        fileTypes.put("pdf", 3);
        fileTypes.put("txt", 3);
        fileTypes.put("doc", 3);
        fileTypes.put("docx", 3);
        // 更多阅读文件格式...
        fileTypes.put("epub", 4);
    }

    public static int getFileType(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return -1; // 文件名无效
        }

        String extension = getExtension(fileName);
        if (extension == null || extension.isEmpty()) {
            return 0; // 未知文件类型
        }
        return fileTypes.getOrDefault(extension.toLowerCase(), 0);
    }

    private static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    /**
     * 获取当前Bucket下的文件列表，可通过前缀Owner来筛选文件
     *
     * @param owner 前缀名或父文件名
     * @return
     * @throws ClientException
     */
    @Override
    public List getFileList(String owner) throws ClientException {
        OSS ossClient = getInfo();
        // 填写Bucket名称，例如examplebucket。
        // 指定每页列举**个文件。
        int maxKeys = 100;
        // 设置marker，例如objecttest.txt。
        String nextMarker = "";
        try {
            ObjectListing objectListing;
            do {
                objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withPrefix(owner)// // 指定前缀
                        .withMarker(nextMarker).withMaxKeys(maxKeys));
                List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
                List fileFolder = new ArrayList<>();
//                for (OSSObjectSummary sum : sums) {
//                    String path = sum.getKey();
//                    if (path.endsWith("/")){
//                        fileFolder.add(path);// 确保只返回文件夹
//                    }
//                }
                nextMarker = objectListing.getNextMarker();
                return sums;
            } while (objectListing.isTruncated());

        } catch (OSSException oe) {
            log.error("Error Message:" + oe.getErrorMessage());
            log.error("Error Code:{}", oe.getErrorCode());
            log.error("Request ID:" + oe.getRequestId());
            log.error("Host ID:" + oe.getHostId());
        } catch (com.aliyun.oss.ClientException ce) {
            log.error("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    // TODO: 处理文件路径，逻辑暂未明确
    public void checkFilePath(String filePath){
        String[] pathSplit = filePath.split("/");
        // 如果要全部的文件夹嵌套格式，则使用下面代码
        if (filePath.endsWith("/")){
            String[] keys = pathSplit;
//            log.info("文件路径：{}", Arrays.toString(keys));
        }
        log.info("文件名：{}", filePath);
        if (!filePath.endsWith("/") && pathSplit.length>1){//文件夹嵌套下的文件
            log.info("文件路径：{}", Arrays.toString(pathSplit));
            String[] path = Arrays.stream(pathSplit).limit(pathSplit.length - 1).toArray(String[]::new);
            String name = pathSplit[pathSplit.length - 1];
            Medias medias = new Medias();
            medias.setPathSegments(path);
            medias.setFileName(name);
            medias.setFileCreateDate(new Date());
            medias.setFileType(getFileType(name));
            mongoDao.insertDoc(medias);
        }
    }

    @Override
    public Map getFileFolder(){
        Query query = new Query();
        query.fields().include("pathSegments");
        List<Medias> mediasList = mongoDao.findByCondition(query, Medias.class);
        Set<List<String>> uniquePathSegments = new HashSet<>();

        for (Medias media : mediasList) {
            uniquePathSegments.add(Arrays.asList(media.getPathSegments()));
        }

        Map<Integer, Set<String>> nestedMap = new HashMap<>();

        for (List<String> pathSegments : uniquePathSegments) {
            for (int level = 0; level < pathSegments.size(); level++) {
                String segment = pathSegments.get(level);
                nestedMap.computeIfAbsent(level, l -> new HashSet<>()).add(segment);
            }
        }
        return nestedMap;
    }

}
