package com.easy.common.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 检测网络状态，获取MAC地址、IP地址。
 */
public class NetworkUtil {

    /**
     * 网络断开
     */
    public static final int STATE_NO_NETWORK = 0;
    /**
     * wifi网络
     */
    public static final int STATE_WIFI = 1;
    /**
     * 2G、3G、4G网络
     */
    public static final int STATE_MOBILE = 2;

    private static final int IPV4 = 0x04;
    private static final int IPV6 = 0x06;

    /**
     * 获取手机的网络连接状态。
     *
     * @return STATE_NO_NETWORK（网络断开），STATE_WIFI（wifi网络），STATE_MOBILE（2G、3G、4G网络）
     */
    public static int getNetworkState(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMan.getActiveNetworkInfo();
        if (info != null && info.isConnected() && info.isAvailable()) { // 网络可用
            String type = info.getTypeName();
            if (type.equalsIgnoreCase("wifi"))
                return STATE_WIFI;
            else if (type.equalsIgnoreCase("mobile"))
                return STATE_MOBILE;
        }
        // 网络不可用
        return STATE_NO_NETWORK;
    }

    /**
     * 获取mac地址
     *
     * @return 可能会返回null
     */
    public static String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null)
            return wifiInfo.getMacAddress();
        else
            return null;
    }

    /**
     * 获取wifi下的ip地址
     *
     * @return 可能会返回null
     */
    public static String getIPAddressAtWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null) {
            int ip = wifiInfo.getIpAddress();
            return (ip & 0xff) + "." + ((ip >> 8) & 0xff) + "."
                    + ((ip >> 16) & 0xff) + "." + ((ip >> 24) & 0xff);
        } else
            return null;
    }

    /**
     * 获取IPV4地址
     *
     * @return 可能会返回null
     */
    public static String getIPV4Address() {
        return getIPAddress(IPV4);
    }

    /**
     * 获取IPV6地址
     *
     * @return 可能会返回null
     */
    public static String getIPV6Address() {
        return getIPAddress(IPV6);
    }

    // *************************************************************************************
    // 私有方法

    /**
     * 获取ip地址
     *
     * @param ipType ip类型：ipv4、ipv6
     * @return 可能会返回null
     */
    private static String getIPAddress(int ipType) {
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface
                    .getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface netFace = enumeration.nextElement();
                Enumeration<InetAddress> enumAddr = netFace.getInetAddresses();
                while (enumAddr.hasMoreElements()) {
                    InetAddress inetAddr = enumAddr.nextElement();
                    if (!inetAddr.isLoopbackAddress()) { // 是否为本地回环地址（ip4：127.0.0.0；ip6：::1）
                        if (ipType == IPV4 && inetAddr instanceof Inet4Address)
                            return inetAddr.getHostAddress();
                        else if (ipType == IPV6
                                && inetAddr instanceof Inet6Address)
                            return inetAddr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
