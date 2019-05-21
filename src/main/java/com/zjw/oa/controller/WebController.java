package com.zjw.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:48:11
 */
@Controller
public class WebController {

    /**
     * Description 跳转登录
     *
     * @return String
     * @author ZhengJiawei
     * @date 2019-03-20 16:28:37
     */
    /*@RequestMapping(value = "/")
    public String index() {
        return "redirect:/user/login";
    }*/
    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            return "-1";
        }

        String fileName = file.getOriginalFilename();

        //加个时间戳，尽量避免文件名称重复
        String path = "D:/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return "-2";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest); //保存文件
        } catch (IOException e) {
            return "-3";
        }

        return "0";
    }

    @RequestMapping("/downloadFile")
    @ResponseBody
    public String downloadFile(HttpServletResponse response) {
        String filePathName = "D:/Users/Downloads/cc.txt";
        File file = new File(filePathName);
        if (!file.exists()) {
            return "-1";
        }

        response.reset();
        response.setHeader("Content-Disposition", "attachment;fileName=" + filePathName);

        try {
            InputStream inStream = new FileInputStream(filePathName);
            OutputStream os = response.getOutputStream();

            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = inStream.read(buff)) > 0) {
                os.write(buff, 0, len);
            }
            os.flush();
            os.close();

            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "-2";
        }

        return "0";
    }

}
