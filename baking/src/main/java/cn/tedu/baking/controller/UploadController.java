package cn.tedu.baking.controller;

import cn.tedu.baking.response.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/")
public class UploadController {
    @Value("${filePath}")
    private String dirPath;

    @PostMapping("upload")
    public JsonResult upload(MultipartFile file) throws IOException {
        //得到上传图片的名称   a.jpg
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        // 得到后缀.jpg
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //得到唯一的文件名,  UUID.randomUUID()得到唯一标识符
        fileName = UUID.randomUUID() + suffix;
        System.out.println(fileName);

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("/yyyy/MM/dd/");
        //得到日期相关的路径      /2023/7/12/
        String datePath = simpleDateFormat.format(new Date());
        //创建文件对象
        File dirFile = new File(dirPath + datePath);
        //判断如果文件不存在则创建
        if (!dirFile.exists()) {
            dirFile.mkdirs();//创建
        }
        //把图片保存到指定路径   异常抛出
        file.transferTo(new File(dirPath + datePath + fileName));
        //上传成功后 把图片路径响应给客户端  /2023/07/12/xxxx.jpg
        return JsonResult.ok(datePath + fileName);
    }

    @PostMapping("remove")
    public JsonResult remove(String url) {
        //删除路径对应的磁盘中的文件
        new File(dirPath + url).delete();
        return JsonResult.ok();
    }


}
