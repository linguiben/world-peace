/**
 * @className FileSaveService
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-23 23:02
 */
package com.jupiter.admin.service.impl;

import com.jupiter.admin.service.FileSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;


/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-03-23 23:02
 */
@Slf4j
@Service
public class FileSaveServiceImpl implements FileSaveService {

    @Value("${file-upload-path}")
    private String fileUploadPath;

    @Resource // 默认按bean名称注入
    private String applicationHomePath;

    @Override
    public String save(MultipartFile multipartFile, String desc){
        if (multipartFile.isEmpty()) {
            return "文件为空，请重新上传";
        }

        try {
            // 获取文件名
            String fileName = multipartFile.getOriginalFilename();
            // 获取文件的字节
            byte[] bytes = multipartFile.getBytes();

            // 这里可以添加你的文件保存逻辑
            File folder=new File(applicationHomePath+"/"+fileUploadPath);
            if(!folder.isDirectory()){
                folder.mkdirs();
            }
            multipartFile.transferTo(new File(folder, fileName)); // 保存文件
            log.info("file:{} saved to {}",fileName,new File(folder, fileName).getAbsolutePath()); //输出（上传文件）保存的绝对路径
            return "文件上传成功：" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败：" + e.getMessage();
        }
    }
}
