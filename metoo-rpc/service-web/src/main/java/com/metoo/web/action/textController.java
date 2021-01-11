package com.metoo.web.action;

import com.loongya.core.util.RE;
import com.loongya.core.util.imageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class textController {

        @PostMapping("/sctx")
    public RE sctx(@RequestParam(name="file") MultipartFile file) throws IOException {
        return new imageUtil().saveImage(file);
    }
}
