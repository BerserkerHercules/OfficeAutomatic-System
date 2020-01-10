package com.zjw.oa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.zjw.oa.entity.MyFile;
import com.zjw.oa.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private static String FILE_PATH = "D:/test/";

    @RequestMapping(value = "/upload")
    @ResponseBody
    @CrossOrigin
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = FILE_PATH;
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("/batch")
    @ResponseBody
    @CrossOrigin
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = FILE_PATH;
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

    @GetMapping("/download")
    @ResponseBody
    @CrossOrigin
    public String downloadFile(String fileName, HttpServletResponse response,HttpServletRequest request) throws Exception {
        //fileName="任务(1).txt";
        String userAgent = request.getHeader("User-Agent");
        String pathName=FILE_PATH+fileName;
        if (fileName != null) {
            //设置文件路径
            File file = new File(pathName);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                //response.setContentType("application/octet-stream;charset=UTF-8");
                //response.setContentType("application/force-download");// 设置强制下载不打开
                //response.reset();
                // 针对IE或者以IE为内核的浏览器：
                if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                } else {
                    // 非IE浏览器的处理：
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                }
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
                //response.setContentType("application/octet-stream;charset=gbk");
                //response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                InputStream inStream = null;
                OutputStream os = null;
                try {
                    inStream = new FileInputStream(pathName);
                    os = response.getOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = inStream.read(buff)) > 0) {
                        os.write(buff, 0, len);
                    }

                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (os != null) {
                        try {
                            os.flush();
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inStream != null) {
                        try {
                            inStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @RequestMapping(value = "/fileList")
    @ResponseBody
    @CrossOrigin
    public JSONArray fileList(String fileName) {
        File file = new File(FILE_PATH);
        File[] fileList = file.listFiles();

        List<MyFile> list = new ArrayList<>();
        for (int i = 0; i < fileList.length; i++) {
            MyFile myFile = new MyFile();
            myFile.setFileName(fileList[i].getName());
            myFile.setFileLength(convertFileSize((fileList[i].length())));
            if(!StringUtils.isNullOrEmpty(fileName)&&fileList[i].getName().equals(fileName)){
                list.add(myFile);
                break;
            }else{
                list.add(myFile);
            }
        }
        String jsonStr = JsonUtil.serializeDate(list);
        return JSON.parseArray(jsonStr);

        /*for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                System.out.println("文件：" + fileName);
            }
            if (fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                System.out.println("目录：" + fileName);

            }
        }*/
    }

    public  static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }



}
