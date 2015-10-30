/**
 * 员工数据批量导入 Controller
 */
package com.honoka.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class StaffImportController {
    @RequestMapping(value = "/uploadStaffFile", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        System.out.println("In /uploadStaffFile");
        System.out.println(multipartFile.getOriginalFilename() + " is uploaded: " + multipartFile.getSize() + " bytes");
        // TODO：保存文件路径
        File file = new File("D:/pending.xls");
        multipartFile.transferTo(file);
        // 本地读取
        // 解析文件
        // 转换成对应 POJO
        // Service
        return "Uploaded: " + multipartFile.getSize() + " bytes";
    }
}
