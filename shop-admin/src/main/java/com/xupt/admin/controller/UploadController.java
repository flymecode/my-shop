package com.xupt.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author maxu
 * @date 2019/6/3
 */
@Controller
public class UploadController {
    @ResponseBody
    @PostMapping("/upload")
    public  Map<String, Object> upload(MultipartFile dropFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String fileName = dropFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = request.getSession().getServletContext().getRealPath("F:\\JavaWorkSapce\\shop\\shop-admin\\src\\main\\resources\\upload");
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(filePath, UUID.randomUUID()+ fileSuffix);
        try {
            dropFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("fileName", file.getName());
        return result;
    }
}
