package com.easy.common.device;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.easy.common.DeviceActivity;

public class DeviceActivityTest extends ActivityUnitTestCase<DeviceActivity> {

    private Intent mIntent;

    public DeviceActivityTest() {
        super(DeviceActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mIntent = new Intent(getInstrumentation().getTargetContext(), DeviceActivity.class);
    }

    @SmallTest
    public void testStartDeviceRunner() {
        startActivity(mIntent, null, null);
        ((DeviceActivity) getActivity()).startDeviceRunner();
    }

}
