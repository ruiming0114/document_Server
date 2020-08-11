package com.document.controller;

import com.document.pojo.JsonResult;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ImgUploadController {

    //上传地址
    @Value("/usr/doc-webapp/user_images/")
    //@Value("D:/images/")
    private String filePath;

    // 执行上传
    @PostMapping("/uploadUserImage")
    public JsonResult<Object> upload(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
        // 获取上传文件名
        String filename = file.getOriginalFilename();

        //改名
        assert filename != null;
        if (filename.length()==0 || !filename.contains(".")){
            return new JsonResult<>("1","图片上传格式错误");
        }

        String suffix = filename.substring(filename.lastIndexOf(".")+1);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
        filename = sdf.format(date) + "." + suffix;

        // 定义上传文件保存路径
        String path = filePath;
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            boolean b = filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + filename));
            String url = request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort() + "/user_images/" + filename;
            Map<String,Object> map  = new HashMap<>();
            map.put("url",url);
            return new JsonResult<>(map,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonResult<>("2","上传失败");
    }
}