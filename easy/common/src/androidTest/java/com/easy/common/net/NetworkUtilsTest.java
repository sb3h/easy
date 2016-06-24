package com.easy.common.net;

import android.test.AndroidTestCase;

public class NetworkUtilsTest extends AndroidTestCase {

    public void testGetNetworkState() {
        int state = NetworkUtils.getNetworkState(getContext());
        boolean stateFlag = false;
        if (state == NetworkUtils.STATE_NO_NETWORK
                || state == NetworkUtils.STATE_WIFI || state == NetworkUtils.STATE_MOBILE)
            stateFlag = true;
        assertEquals("不能正确获取到网络状态：" + state, true, stateFlag);
    }

    public void testGetMacAddress() {
        String mac = NetworkUtils.getMacAddress(getContext());
        assertNotNull("MAC地址为null", mac);
    }

    public void testGetIPAddressAtWifi() {
        String ip = NetworkUtils.getIPAddressAtWifi(getContext());
        assertNotNull("wifi下的IP地址为null", ip);
    }

    public void testGetIPV4Address() {
        String ipV4 = NetworkUtils.getIPV4Address();
        assertNotNull("IPV4地址为null", ipV4);
    }

    public void testGetIPV6Address() {
        String ipV6 = NetworkUtils.getIPV6Address();
        assertNotNull("IPV6地址为null", ipV6);
    }

}
