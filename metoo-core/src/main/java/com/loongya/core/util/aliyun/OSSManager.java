package com.loongya.core.util.aliyun;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public class OSSManager {


    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";


    private static String accessKeyId = "LTAI4FiaxuSoqntgS2jLudGy";
    private static String accessKeySecret = "j6seJFghda0hTyCkmAuFVspkzD8pLc";
    private static String bucketName = "learn-info";

    protected static final String IMG_TEACHER_KEY = "img/teacher/";
    protected static final String IMG_STUDENT_KEY = "img/student/";
    protected static final String IMG_ADMIN_KEY = "img/admin/";
    protected static final String MP4_TEACHER_KEY = "mp4/teacher/";
    protected static final String MP4_STUDENT_KEY = "mp4/student/";
    protected static final String MP4_ADMIN_KEY = "mp4/admin/";



    private static OSS getOssClient(){

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (ossClient.doesBucketExist(bucketName)) {
        } else {
            ossClient.createBucket(bucketName);
        }
        return ossClient;
    }


    /**
     * 通用上传方法
     */
    protected static OSSModel upload(InputStream is,String pre, String imgname){
        OSS ossClient = null;
        try {
            ossClient = getOssClient();
            String path = pre + imgname;
            ossClient.putObject(bucketName, path, is);
            return new OSSModel(OSSUtil.PATH_PRE + path, path);
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
        return null;
    }

}
