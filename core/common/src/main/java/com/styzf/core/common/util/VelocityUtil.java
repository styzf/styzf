package com.styzf.core.common.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 模板引擎工具类
 * @author styzf
 * @date 2018年8月1日 
 *
 */
public class VelocityUtil {

    private static Logger logger = LoggerFactory.getLogger(VelocityUtil.class);
      
    /**
     * 
     * @author styzf
     * @date 2018年8月1日 
     * @param templatePath
     *      模板路径
     * @param paramMap
     *      参数
     * @return 根据模板和参数生成的字符串数据
     */
    public static String exportFixedVelocity(String templatePath, Map<String, Object> paramMap) {
        // 创建引擎
        VelocityEngine ve = new VelocityEngine();
        // 加载方式1：设置模板加载路径，这里设置的是class下
        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        /*// 加载方式2 资源加载方式为jar
        ve.setProperty(Velocity.RESOURCE_LOADER, "jar");
        // 设置velocity资源加载方式为file时的处理类  
        ve.setProperty("jar.resource.loader.class", "org.apache.velocity.runtime.resource.loader.JarResourceLoader");  
        // 设置jar包所在的位置  
        ve.setProperty("jar.resource.loader.path", "jar:file:WebRoot/WEB-INF/lib/vm.jar");*/
        ve.setProperty(RuntimeConstants.ENCODING_DEFAULT, "UTF-8");
        ve.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        ve.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
        ve.setApplicationAttribute("toolbox.config.location","classpath:toolbox.xml");
        StringWriter writer = null;
        try {
            // 进行初始化操作
            ve.init();
            // 加载模板，设定模板编码
            Template t = ve.getTemplate(templatePath);
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                Object value = entry.getValue();
                entry.setValue(value == null ? "" : value);
            }
            // 设置初始化数据
            VelocityContext context = new VelocityContext(paramMap);
            // 设置输出
            writer = new StringWriter();
            // 将环境数据转化输出
            t.merge(context, writer);
            return writer.toString();
        } catch (Exception e) {
            logger.error("模版转化错误!", e);
            throw new RuntimeException("模版转化错误!", e);
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }
}