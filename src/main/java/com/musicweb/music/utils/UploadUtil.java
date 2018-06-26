package com.musicweb.music.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.FileInputStream;

public class UploadUtil {

    private static final String ACCESS_KEY = "pGoCaQuqxMI7B7FAcKK-5y-jHb4FeSkZh21N0IpK";
    private static final String SECRET_KEY = "cOqxbDMGBwDPlGbQQiM1N7yy6FV54ApMH65p5E0J";
    private static final String BUCKET = "darkmusic";
    private static final String PATH = "http://p9wf9tyl0.bkt.clouddn.com";
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private static Zone z = Zone.autoZone();
    private static Configuration c = new Configuration(z);
    private static UploadManager uploadManager = new UploadManager(c);

    //普通上传
    public static String commonUpload(FileInputStream fileInputStream, String fileName, String fileType) {
        fileName = fileName + fileType;
        String uploadToken = auth.uploadToken(BUCKET);
        return upload(fileInputStream, fileName, uploadToken);
    }

    public static String commonUpload(FileInputStream fileInputStream, String fileName){
        String uploadToken = auth.uploadToken(BUCKET);
        return upload(fileInputStream, fileName, uploadToken);
    }

    //覆盖上传
    public static String coverUpload(FileInputStream fileInputStream, String fileName, String fileType) {
        fileName = fileName + fileType;
        String uploadToken = auth.uploadToken(BUCKET, fileName);
        return upload(fileInputStream, fileName, uploadToken);
    }


    private static String upload(FileInputStream fileInputStream, String fileName, String uploadToken) {

        try {
            Response response = uploadManager.put(fileInputStream, fileName, uploadToken, null, null);
            System.out.println(response.bodyString());
            String path = PATH + "/" + fileName;
            return path;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
                return null;
            } catch (QiniuException e1) {
                //ignore
                return null;
            }
        }
    }
}
