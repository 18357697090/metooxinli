package com.metoo.web.controller.upload;

import com.loongya.core.util.RE;
import com.loongya.core.util.imageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class image {

    @RequestMapping("/image")
    public RE image(@RequestParam(name="file") MultipartFile file){
        return imageUtil.saveImage(file);
    }
}
