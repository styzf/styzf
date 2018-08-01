package com.styzf.core.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IP工具类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
public class IPUtils {
    static final Logger logger = LoggerFactory.getLogger(IPUtils.class);
    /**
     * 获取当前主机的IP地址
     * @author styzf
     * @date 2018年7月28日 
     * @return
     */
    public static String getLocalIpAddress() {
      try {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
      } catch (UnknownHostException e) {
        logger.error("获取本地IP异常", e);
      }
      return "localhost";
    }
}
