/**
 * @className FileSaveService
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-03-23 23:36
 */
package com.jupiter.admin.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *@desc Save file to servers.
 *@author Jupiter.Lin
 *@date 2024-03-23 23:36
 */
public interface FileSaveService {
    String save(MultipartFile multipartFile, String desc) throws IOException;
}
