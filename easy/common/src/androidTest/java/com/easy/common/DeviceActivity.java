package com.easy.common;

import android.app.Activity;
import android.os.Bundle;

import com.easy.common.device.DeviceTestRunner;

public class DeviceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startDeviceRunner() {
        DeviceTestRunner testRunner = new DeviceTestRunner(this);
        testRunner.runTest();
    }

}
