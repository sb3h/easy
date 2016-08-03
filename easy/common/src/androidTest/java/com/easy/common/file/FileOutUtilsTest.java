package com.easy.common.file;

import android.os.Environment;
import android.test.AndroidTestCase;

import java.io.File;

public class FileOutUtilsTest extends AndroidTestCase {

    private static final String EASY_DIR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "easy";
    private static final String COMMON_DIR_NAME = "common";

    public void testBufferedWriter() {
        File file = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_NAME + File.separator + "bufferedWriter.txt");
        String[] array = new String[]{"hello", "world", "hi", "鸣人"};
        FileOutUtils.bufferedWriter(file, true, true, array);
    }

}
