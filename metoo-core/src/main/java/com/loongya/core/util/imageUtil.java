package com.loongya.core.util;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class imageUtil {

    private static final List<String> ALLOW_TYPES= Arrays.asList("image/jpeg","image/png","image/bmp","image/jpg");

    public static RE saveImage(MultipartFile file){
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
            String path="/usr/local/image/"+year+'/'+hourse+'/'+day+'/';
            File files =new File(path);
            if(!files.exists()){
                files.mkdirs();
            }
            File dest = new File(path + originalFilename);
            Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.25f).toFile(dest);
            return RE.ok("http://www.metooxinli:8088/image/"+year+'/'+hourse+'/'+day+'/'+originalFilename);
        } catch (Exception e) {
            return RE.fail("Failed to save image");
        }
    }


}
