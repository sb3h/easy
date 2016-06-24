package com.easy.common.device;

import junit.framework.TestSuite;

public class DeviceTestSuite extends TestSuite {

    public DeviceTestSuite() {
        addTestSuite(ScreenUtilsTest.class);
    }

}
