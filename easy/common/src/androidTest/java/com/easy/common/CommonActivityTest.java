package com.easy.common;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class CommonActivityTest extends ActivityUnitTestCase<CommonActivity> {

    private Intent mIntent;

    public CommonActivityTest() {
        super(CommonActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mIntent = new Intent(getInstrumentation().getTargetContext(), CommonActivity.class);
    }

    @MediumTest
    public void testStartDeviceRunner() {
        startActivity(mIntent, null, null);
        ((CommonActivity) getActivity()).startCommonRunner();
    }

}
