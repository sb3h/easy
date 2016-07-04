package com.easy.common;

import android.app.Activity;
import android.test.AndroidTestRunner;

public class CommonTestRunner extends AndroidTestRunner {

    public CommonTestRunner(Activity activity) {
        setContext(activity);
        setTest(new CommonTestSuite());
    }

}
