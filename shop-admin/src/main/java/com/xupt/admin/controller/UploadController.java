package com.xupt.admin.controller;

import com.xupt.admin.service.IQiniuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maxu
 * @date 2019/6/3
 */
@Controller
@Slf4j
public class UploadController {
    @Autowired
    private IQiniuService iQiniuService;
    @ResponseBody
    @PostMapping("/upload")
    public  Map<String, Object> upload(MultipartFile dropFile) throws IOException {
        Map<String, Object> result = new HashMap<>();
        byte[] datas = dropFile.getBytes();
        String imgUrl = iQiniuService.upload(datas);
        log.info(imgUrl);
        String fileName = dropFile.getOriginalFilename();
        result.put("error", 0);
        result.put("data", new String[]{fileName});
        // 图片一和图片二 需要填写的路径
        result.put("fileName", fileName);
        return result;
    }
}
