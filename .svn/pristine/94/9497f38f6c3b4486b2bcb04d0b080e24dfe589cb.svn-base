package com.puckteam.sns.core.support.util;

import com.puckteam.sns.core.constant.CoreConfig;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by PoemWhite on 2017/4/10.
 */
@Component
public class ImageUploadUtil {

    static Logger logger = LogManager.getLogger();

    /**
     * 单图片上传
     * @param request
     * @param uploadFile
     * @param targetDir
     * @param fileId
     * @return
     * @throws IOException
     */
    public String upload(HttpServletRequest request,
                       MultipartFile uploadFile,
                       String targetDir,
                       String fileId) throws IOException{

        return upload(request,uploadFile,targetDir,fileId,null);
    }

    /**
     * 单图片上传(提供回调函数)
     * @param request
     * @param uploadFile
     * @param targetDir
     * @param fileId
     * @param callback
     * @return
     * @throws IOException
     */
    public String upload(HttpServletRequest request,
                         MultipartFile uploadFile,
                         String targetDir,
                         String fileId,
                         ImageUploadCallback callback) throws IOException{

        //上传路径
        String uploadPath = CoreConfig.getInstance().getPackingConfig().getImageUploadUrl();

        //开发模式取本地
        if (CoreConfig.getInstance().isDevelopMode()) {
            uploadPath = request.getRealPath(uploadPath);
        }

        String targetPath = uploadPath + "/" + targetDir;

        File file = new File(targetPath);
        if (!file.exists())
            file.mkdirs();

        if (request instanceof MultipartRequest) {

            if (!uploadFile.isEmpty()) {
                String type = uploadFile.getOriginalFilename()
                        .substring(uploadFile.getOriginalFilename().indexOf("."));// 取文件格式后缀名

                String filename = fileId + type;// 文件名

                String destPath = targetPath + "/" + filename;

                File destFile = new File(destPath);

                try {
                    // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                    FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), destFile);// 复制临时文件到指定目录下
                } catch (IOException e) {
                    logger.error(e);
                    throw e;
                }

                if (callback !=null){
                    callback.uploaded(destPath);
                }

                return filename;
            }
        }

        return null;
    }

}
