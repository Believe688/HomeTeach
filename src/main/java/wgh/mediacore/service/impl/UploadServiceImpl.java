package wgh.mediacore.service.impl;

import wgh.mediacore.service.UploadFileService;

/**
 * @TIME 2024/10/13
 * @USER Linn
 */
public class UploadServiceImpl implements UploadFileService {
    /**
     * 两种逻辑
     * 一、浏览器直接分片上传到OSS，风险较大，上传完成后，通知服务器更新
     * 二、浏览器分片上传到服务器，服务器简单传输到OSS，较安全
     * 目前采用一
     */
    public void uploadFileToAli() {
        // 目前仅接受通知

    }
}
