package com.easy.common.device;

import android.test.ActivityInstrumentationTestCase2;

import com.easy.common.DeviceActivity;

public class DeviceTestActivity extends ActivityInstrumentationTestCase2<DeviceActivity> {

    public DeviceTestActivity() {
        super(DeviceActivity.class);
        DeviceTestRunner testRunner = new DeviceTestRunner(getActivity());
        testRunner.runTest();
    }

}
