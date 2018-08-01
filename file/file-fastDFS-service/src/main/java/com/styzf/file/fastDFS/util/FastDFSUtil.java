//package com.styzf.file.fastDFS.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//import org.apache.commons.io.FilenameUtils;
//import org.csource.common.MyException;
//import org.csource.fastdfs.ClientGlobal;
//import org.csource.fastdfs.StorageClient;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * 调用该方法需要创建配置文件fastdfs_client.conf和application.properties
// * 
// * fastdfs_client.conf需要配置：
// * # 配置追踪服务器的地址
// * tracker_server=服务器ip地址:端口号
// * 
// * application.properties需要配置：
// * fastDFS_server_url=http://服务器的ip地址
// * 
// * @ClassName: FastDFSUtil 
// * @Description: 一个用于处理fastDFS的工具类，提供了基础的上传，下载和删除的方法，定义了getset方法获取设置各个参数
// * @author 杨镇发
// *
// */
//public class FastDFSUtil {
//    private FastDFSUtil (){}
//    /** 加载fastdfs_client.conf文件 */
//    private static String conf_filename = FastDFSUtil.class
//            .getResource("/fastDFS_client.conf").getPath();
//    /** 配置文件的地址 */
//    private static String applicationPath = FastDFSUtil.class
//            .getResource("/fastDFS.properties").getPath();
//    /**
//     * 服务器的url
//     */
//    private static String fastDFSServerUrl;
//    
//    static{
//        try {
//            /** 读取配置文件，获取参数值  */
//            Properties properties = new Properties();
//            properties.load(new FileInputStream(new File(applicationPath)));
//            fastDFSServerUrl = properties.getProperty("fastDFS_server_url");
//            /** 初始化客户端全局对象 */
//            ClientGlobal.init(conf_filename);
//        } catch (IOException | MyException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    /** 创建存储客户端对象 */
//    private static StorageClient storageClient = new StorageClient();
//    
//    /**
//     * 上传文件到客户端，该方法会抛出运行时异常
//     * @Title: updateFile 
//     * @param multipartFile
//     * @return String[]  服务器返回一个数组，第一个参数为保存在哪个组，第二个参数表示保存的位置，图片访问路径：http://服务器ip/组名/地址
//     * @throws
//     */
//    public static String[] updateFile(MultipartFile multipartFile) {
//        /** 获取上传的文件名  */
//        String originalFilename = multipartFile.getOriginalFilename();
//        
//        /** 上传文件到FastDFS文件服务器 */
//        try {
//            String[] strArr = storageClient.upload_file(multipartFile.getBytes(),
//                        FilenameUtils.getExtension(originalFilename), null);
//            return strArr;
//        } catch (IOException | MyException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//    
//    /**
//     * 
//     * @Title: download_file 
//     * @Description: 下载文件,该方法可能会抛出运行时异常
//     * @param group 组名
//     * @param path 路径
//     * @return byte[]  数据
//     */
//    public byte[] download_file(String group , String path) {
//        /**
//         * [group1, M00/00/00/wKgMgFlHqumASKsWAABonuLw4M4076.jpg]
//         * 数组中第一个元素：group1组的名称
//         * 数组中第二个元素：文件存储路径
//         */
//        try {
//            byte[] data= storageClient.download_file(group , path);
//            return data;
//        } catch (IOException | MyException e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }
//
//    /**
//     * 
//     * @Title: deleteFile 
//     * @Description: 删除服务器文件，该方法可能会抛出运行时异常
//     * @param group 组名
//     * @param path 路径 
//     * @return void    返回类型 
//     * @throws
//     */
//    public void deleteFile(String group , String path) {
//        /**
//         * [group1, M00/00/00/wKgMgFlHqumASKsWAABonuLw4M4076.jpg]
//         * 数组中第一个元素：group1组的名称
//         * 数组中第二个元素：文件存储路径
//         */
//        try {
//            int res = storageClient.delete_file(group , path);
//        } catch (Exception e) {
//            throw new RuntimeException("");
//        }
//    }
//    
//    public static String getConf_filename() {
//        return conf_filename;
//    }
//
//    public static void setConf_filename(String conf_filename) {
//        FastDFSUtil.conf_filename = conf_filename;
//    }
//
//    public static String getApplicationPath() {
//        return applicationPath;
//    }
//
//    public static void setApplicationPath(String applicationPath) {
//        FastDFSUtil.applicationPath = applicationPath;
//    }
//
//    public static String getFastDFSServerUrl() {
//        return fastDFSServerUrl;
//    }
//
//    public static void setFastDFSServerUrl(String fastDFSServerUrl) {
//        FastDFSUtil.fastDFSServerUrl = fastDFSServerUrl;
//    }
//
//    public static StorageClient getStorageClient() {
//        return storageClient;
//    }
//
//    public static void setStorageClient(StorageClient storageClient) {
//        FastDFSUtil.storageClient = storageClient;
//    }
//    
//}
//
