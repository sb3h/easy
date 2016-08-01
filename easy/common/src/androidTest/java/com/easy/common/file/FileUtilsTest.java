package com.easy.common.file;

import android.os.Environment;
import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

import java.io.File;

public class FileUtilsTest extends AndroidTestCase {

    private static final String EASY_DIR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "easy";
    private static final String COMMON_DIR_NAME = "common";

    public void testCreateDir() {
        File parentDir = new File(EASY_DIR_PATH);
        File newDir = FileUtils.createDir(parentDir, COMMON_DIR_NAME);
        LogCatUtils.log(newDir.getAbsolutePath());
    }

    public void testBufferedWriter() {
        File file = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_NAME + File.separator + "bufferedWriter.txt");
        String[] array = new String[]{"hello", "world", "hi", "鸣人"};
        FileUtils.bufferedWriter(file, true, true, array);
    }

    public void testCompressDir() {
        File targetDir = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_NAME);
        File zipFile = new File(EASY_DIR_PATH + File.separator + "common.zip");
        zipFile = FileUtils.compressDir(targetDir, zipFile);
        assertNotNull(zipFile);
    }

    public void testCompressFile() {
        File targetFile = new File(EASY_DIR_PATH + File.separator + "common1.txt");
        File zipFile = new File(EASY_DIR_PATH + File.separator + "common1.zip");
        zipFile = FileUtils.compressFile(targetFile, zipFile);
        assertNotNull(zipFile);
    }

}
