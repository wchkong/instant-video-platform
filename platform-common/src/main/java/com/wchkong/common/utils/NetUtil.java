package com.wchkong.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

/**
 * zhangwei
 */
public class NetUtil {
    private static final Logger LOG = LoggerFactory.getLogger(NetUtil.class);

    public static Collection<String> getAllLocalIP() throws Exception {
        ArrayList<String> ar = new ArrayList<String>();
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        if (netInterfaces == null) {
            return ar;
        }
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> inetAdrsEnum = ni.getInetAddresses();
            if (inetAdrsEnum == null || !inetAdrsEnum.hasMoreElements()) {
                continue;
            }
            InetAddress ip = inetAdrsEnum.nextElement();
            if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                //				System.out.println("Interface " + ni.getName() + " seems to be InternetInterface. I'll take it...");
            } else {
                ar.add(ip.getHostAddress());
            }
        }
        return ar;
    }

    /**
     * @param ip
     * @return 判断指定ip是否在本机的ip列表范围内，使用场景：限定某一台机器单机运行任务
     */
    public static boolean containsIp(String ip) {
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();

                Enumeration<InetAddress> emu = ni.getInetAddresses();
                while (emu.hasMoreElements()) {
                    InetAddress ipaddr = emu.nextElement();
                    if (ip.equals(ipaddr.getHostAddress()))
                        return true;
                }
            }
        } catch (SocketException e) {
            LOG.error("error during containsIp,ip:" + ip, e);
        }
        return false;
    }

    /**
     * @param ips
     * @param separator
     * @return 检查参数ips中是否包含本地服务器ip
     */
    public static boolean containsIps(String ips, String separator) {
        if (StringUtils.isEmpty(ips)) {
            return false;
        }
        String[] parts = ips.split(separator);
        for (String s : parts) {
            if (containsIp(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取本机ip
     *
     * @return
     */
    public static String getLocalIp() {
        String ip = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString(); //获取本机ip
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}