package com.yui.study.crawler.util;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件操作
 *
 * @author XuZhuohao
 * @date 2018/11/21
 */
public class FileUtil {
    private final static int BUFFER_SIZE = 2 * 1024;

    /**
     * 保存上传的文件到临时目录
     *
     * @param file 上传的MultipartFile文件
     * @param path 临时目录
     * @return
     */
    public static String saveMultipartFile(MultipartFile file, String path) {
        // 1.保存文件保存目录的存在
        mkdirDirectory(path);
        // 2.生成保存文件的路径
        String filePath = path + File.separator + file.getOriginalFilename();
        // 3.保存文件到指定路径中
        File wordFile = new File(filePath);
        try (OutputStream os = new FileOutputStream(wordFile)) {
            os.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存临时文件失败，" + filePath);
        }
        return wordFile.getAbsolutePath();
    }

    /**
     * 创建文件保存目录
     *
     * @param path 路径
     */
    public static void mkdirDirectory(String path) {
        File file = new File(path);
        if (path.contains(".") && !file.exists()) {
            System.out.println("创建文件夹：" + file.getParentFile().getAbsolutePath() + " ，结果：" + file.getParentFile().mkdirs());
        } else if (!file.exists()) {
            System.out.println("创建文件夹：" + file.getAbsolutePath() + " ，结果：" + file.mkdirs());
        }
    }

    /**
     * 压缩文件
     *
     * @param path          文件路径(也可以文件)
     * @param keepSourceFileStructure 保存被压缩文件夹
     * @param keepAllFileStructure    保存所有文件的目录结构，为true时，压缩文件中目录结构不变；
     *                                为false时，被压缩文件夹目录中所有文件移到根目录(或者压缩文件
     *                                夹目录下，取决于参数{keepSourceFileStructure})，这时候有可能
     *                                出现相同文件名的情况，导致压缩失败
     * @return 压缩文件对象
     */
    public static File zipFiles(String path, boolean keepSourceFileStructure, boolean keepAllFileStructure) {
        File directory = new File(path);
        if (!directory.exists()) {
            throw new RuntimeException("压缩失败，文件 " + directory.getAbsolutePath() + " 不存在");
        }
        File zipFile;
        if (!directory.isDirectory()) {
            zipFile = new File(directory.getParent() + File.separator + directory.getName() + ".zip");
        } else {
            zipFile = new File(path + ".zip");
        }
        try (OutputStream os = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(os)) {
            compress(directory, zos, directory.getName(), keepSourceFileStructure, keepAllFileStructure);
            return zipFile;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("压缩文件出现错误");
        }
    }

    /**
     * 压缩文件
     *
     * @param sourceFile              文件
     * @param zos                     压缩文件流
     * @param name                    文件名称
     * @param keepSourceFileStructure 保存被压缩文件夹
     * @param keepAllFileStructure    保存所有文件的目录结构，为true时，压缩文件中目录结构不变；
     *                                为false时，被压缩文件夹目录中所有文件移到根目录(或者压缩文件
     *                                夹目录下，取决于参数{keepSourceFileStructure})，这时候有可能
     *                                出现相同文件名的情况，导致压缩失败
     * @throws IOException IOException
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean keepSourceFileStructure, boolean keepAllFileStructure) throws IOException {
        List<File> sourceFiles = new ArrayList<>();
        // 是否保留压缩文件夹结构
        String structure = "";
        if (keepSourceFileStructure) {
            structure = name + "/";
        }
        if (sourceFile.isDirectory()) {
            // 往list中添加文件
            sourceFiles.addAll(getFiles(sourceFile));
        } else {
            sourceFiles.add(sourceFile);
        }
        if (!keepAllFileStructure) {
            Set<String> sameFiles = validSameFile(sourceFiles);
            if (sameFiles.size() > 0) {
                throw new RuntimeException("压缩文件，存在相同的文件名，请修改 keepAllFileStructure 为 true, 或者修改文件名:\r\n"
                + JSON.toJSONString(sameFiles));
            }
        }
        // 空目录处理
        if (sourceFiles.size() == 0) {
            // 空目录是否要添加结构
            if (keepSourceFileStructure) {
                zos.putNextEntry(new ZipEntry(structure));
                zos.closeEntry();
            }
        } else {
            // 逐个文件添加到压缩文件流中
            for (File tempFile : sourceFiles) {
                // 获取文件名
                String fileName = structure + tempFile.getName();
                // 如果需要保持文件目录，重构文件名称
                if (keepAllFileStructure) {
                    // 获取文件绝对路径
                    fileName = tempFile.getAbsolutePath();
                    // 将绝对路径 编程目录的相对路径（当前文件的绝对路径去除需要压缩目录的绝对路径）
                    fileName = fileName.replace(sourceFile.getAbsolutePath(), "");
                    // 压缩文件目录信息 + 删除剩余路径的第一个字符(文件分隔符)
                    fileName = structure + fileName.substring(1);
                }
                compress(tempFile, zos, fileName);
            }
        }
    }

    private static Set<String> validSameFile(List<File> sourceFiles) {
        // 保存所有文件的目录结构时，校验是否存在文件名称一样的情况
        Set<String> resultSet = new HashSet<>(16);

        HashMap<String, String> tempHashMap = new HashMap<>(16);
        StringBuffer sameFiles = new StringBuffer();
        for (File file : sourceFiles) {
            if (tempHashMap.get(file.getName()) != null) {
                resultSet.add(tempHashMap.get(file.getName()));
                resultSet.add(file.getAbsolutePath());
                continue;
            }
            tempHashMap.put(file.getName(), file.getAbsolutePath());
        }

        return resultSet;
    }

    /**
     * 添加一个文件到压缩流中
     *
     * @param sourceFile 文件
     * @param zos        压缩文件流
     * @param name       文件名称
     * @throws IOException IOException
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        //向zip输出流中添加一个zip实体,构造器中name为zip实体的文件的名字
        zos.putNextEntry(new ZipEntry(name));
        //copy文件到zip输出流中
        int len;
        FileInputStream in = new FileInputStream(sourceFile);
        while ((len = in.read(buf)) != -1) {
            zos.write(buf, 0, len);
        }
        //Complete the entry
        zos.closeEntry();
        in.close();
    }

    public static void main(String[] args) {
        zipFiles("D:\\Projects\\demo\\libreoffice\\temp\\word\\waterMark", false,false);
    }

    /**
     * 返回所有文件列表
     *
     * @param file 文件
     * @return
     */
    private static List<File> getFiles(File file) {
        List<File> files = new ArrayList<>();
        if (!file.isDirectory()) {
            files.add(file);
            return files;
        }
        File[] tempFiles = file.listFiles();
        if (tempFiles == null || tempFiles.length == 0) {
            return files;
        }
        for (File tempFile : tempFiles) {
            files.addAll(getFiles(tempFile));
        }
        return files;
    }
}
