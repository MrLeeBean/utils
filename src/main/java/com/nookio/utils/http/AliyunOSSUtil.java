package com.nookio.utils.http;



import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by yangbo on 15/7/18.
 */
public class AliyunOSSUtil {

    public static final String ACCESS_ID = "";
    public static final String ACCESS_KEY = "";
    public static final String END_POINT = "";

    public static final String DEV_BUCKETNAME = "";
    public static final String DEV_ALIYUN_HOST = "";

    public static final String PROD_BUCKETNAME = "";
    public static final String PROD_ALIYUN_HOST = "";

    public static final String PATH = "photos/";

    /**
     * 获取OSSClient
     *
     * @return
     */
    public static OSSClient getClient() {
        OSSClient client = new OSSClient(END_POINT, ACCESS_ID, ACCESS_KEY);
        return client;
    }

    /**
     * 上传文件
     * @param isProd 是否是测试环境
     * @param file
     * @throws Exception
     */
    public static String uploadFile(boolean isProd, String fileName, File file) throws Exception {
        OSSClient client = getClient();
        String key = generateKey(fileName);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentType("image/jpg");
        objectMeta.setContentLength(file.length());
        InputStream input = new FileInputStream(file);
        if (isProd) {
            client.putObject(PROD_BUCKETNAME, key, input, objectMeta);
            return PROD_ALIYUN_HOST + "/" + key;
        } else {
            client.putObject(DEV_BUCKETNAME, key, input, objectMeta);
            return DEV_ALIYUN_HOST + "/" + key;
        }
    }

    /**
     * 获取文件名
     *
     * @param fileName
     * @return
     */
    public static String generateFileName(String fileName) {
        return UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }

    /**
     * 获取上传key（阿里云里的key）
     *
     * @param fileName
     * @return
     */
    public static String generateKey(String fileName) {
        return PATH + generateFileName(fileName);
    }

    /**
     * @param isProd
     * @param url
     */
    public static void deleteFile(boolean isProd, String url) {
        OSSClient client = getClient();
        String key = null;
        if (isProd) {
            key = url.replace(PROD_ALIYUN_HOST + "/", "");
            client.deleteObject(PROD_BUCKETNAME, key);
        } else {
            key = url.replace(DEV_ALIYUN_HOST + "/", "");
            client.deleteObject(DEV_BUCKETNAME, key);
        }
    }
}
