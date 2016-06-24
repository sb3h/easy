package com.easy.common.device;

import android.app.Activity;
import android.test.AndroidTestRunner;

public class DeviceTestRunner extends AndroidTestRunner {

    public DeviceTestRunner(Activity activity) {
        setContext(activity);
        setTest(new DeviceTestSuite());
    }

}
