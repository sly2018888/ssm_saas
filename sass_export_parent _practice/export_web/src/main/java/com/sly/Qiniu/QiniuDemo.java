package com.sly.Qiniu;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import javax.crypto.SecretKey;

public class QiniuDemo {
    public static void main(String[] args) {
        //构造一个带制定region对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证 准备上传
        // 1、bucket空间名称：saasclass116
        // 2、分配给我们的临时域名：q1iblbbf1.bkt.clouddn.com
        // 3、AccessKey  SMYBP3MzIbmPBKwzizwy9sZdbhqNwX2ewisKlJJo
        // SecretKey jBWWqfSxw9XWBpnFIpMlBzG3ABnbe7qVCQDouNL_
        String accessKey = "SMYBP3MzIbmPBKwzizwy9sZdbhqNwX2ewisKlJJo";
        String secretKey = "jBWWqfSxw9XWBpnFIpMlBzG3ABnbe7qVCQDouNL_";
        String bucket = "saasclass116";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\syl\\Pictures\\T1.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String s = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, s);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(defaultPutRet.key);
            System.out.println("http://q1iblbbf1.bkt.clouddn.com/" + defaultPutRet.hash);
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
            }
        }


    }



}
