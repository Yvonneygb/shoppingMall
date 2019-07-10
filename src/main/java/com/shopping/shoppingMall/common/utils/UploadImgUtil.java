package com.shopping.shoppingMall.common.utils;

import com.shopping.shoppingMall.common.entity.ResponseVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description 图片上传工具类
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/4 16:32
 * @Version 1.0
 */
public class UploadImgUtil {

    public static ResponseVO uploadImg(MultipartFile imgFile, String fileName) throws IOException {
        ResponseVO responseVO = new ResponseVO();
        if(!imgFile.isEmpty()){
            String realFileName = imgFile.getOriginalFilename();
            String type = null;
            String path = null;
            type = realFileName.indexOf(".") != -1 ?realFileName.substring(realFileName.lastIndexOf(".") + 1, realFileName.length()) : null;
            System.out.println("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("JPEG".equals(type.toUpperCase())|| "PNG".equals(type.toUpperCase())
                        || "JPG".equals(type.toUpperCase()) || "BMP".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    //String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    //String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    fileName = fileName + "." + type;
                    //path = "D:\\img\\" + fileName;
                    path = "/root/app/ziyuan/img/" + fileName;
                    System.out.println("存放图片文件的路径:" + path);
                    imgFile.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
                    responseVO.setData(type);
                }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    responseVO.setEcode(1);
                    responseVO.setEmsg("文件格式错误");
                }
            }else {
                System.out.println("文件类型为空");
                responseVO.setEmsg("文件类型为空");
                responseVO.setEcode(1);
            }
        }
        return responseVO;
    }
}
