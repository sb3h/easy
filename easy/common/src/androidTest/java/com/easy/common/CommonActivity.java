package com.easy.common;

import android.app.Activity;
import android.os.Bundle;

public class CommonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startCommonRunner() {
        CommonTestRunner testRunner = new CommonTestRunner(this);
        testRunner.runTest();
    }

}
