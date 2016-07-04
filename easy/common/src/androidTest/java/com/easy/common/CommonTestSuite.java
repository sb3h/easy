package com.easy.common;

import com.easy.common.date.DateUtilsTest;
import com.easy.common.device.ScreenUtilsTest;
import com.easy.common.net.NetworkUtilsTest;

import junit.framework.TestSuite;

public class CommonTestSuite extends TestSuite {

    public CommonTestSuite() {
        // date包
        addTestSuite(DateUtilsTest.class);
        // device包
        addTestSuite(ScreenUtilsTest.class);
        // net包
        addTestSuite(NetworkUtilsTest.class);
    }

}
