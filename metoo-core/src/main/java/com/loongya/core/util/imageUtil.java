package com.loongya.core.util;


import com.loongya.core.util.aliyun.OSSUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

public class imageUtil {

    private static final String SAVE_PATH = "/usr/local/image/";
//    private static final String SAVE_PATH = "D:\\";

    private static final List<String> ALLOW_TYPES= Arrays.asList("image/jpeg","image/png","image/bmp","image/jpg");

    public static RE saveImage(MultipartFile file,double x){
        if(file.isEmpty()){
            return RE.noData();
        }
        if(!ALLOW_TYPES.contains(file.getContentType())){
            return RE.fail("noType");
        }
        if (file.getSize() >= 10*1024*1024)
        {
            return RE.fail("The file is too large");
        }
        String prefix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        String originalFilename = UUID.randomUUID().toString()+"."+prefix;
        String day = DateUtils.getDay();
        String hourse=DateUtils.getHourse();
        String year=DateUtils.getYear();
        try {
            //存放的目录
            String path=SAVE_PATH +year+'/'+hourse+'/'+day+'/';
            File files =new File(path);
            if(!files.exists()){
                files.mkdirs();
            }
            File dest = new File(path + originalFilename);
            Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(x).toFile(dest);
            String subPath = "/image/"+year+"/"+hourse+"/"+day+"/"+originalFilename;
            Map<String, String> map = new HashMap<>();
            map.put("path", subPath);
            map.put("fullPath", OSSUtil.fillPath(subPath));
            return RE.ok(map);
        } catch (Exception e) {
            return RE.fail("Failed to save image");
        }
    }


}
