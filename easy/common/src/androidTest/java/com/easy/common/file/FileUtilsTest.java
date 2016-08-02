package com.easy.common.file;

import android.os.Environment;
import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

import java.io.File;

public class FileUtilsTest extends AndroidTestCase {

    private static final String EASY_DIR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "easy";
    private static final String COMMON_DIR_NAME = "common";
    private static final String COMMON_DIR_ZIP_NAME = "commonDir.zip";
    private static final String COMMON_FILE_ZIP_NAME = "commonFile.zip";

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
        File zipFile = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_ZIP_NAME);
        zipFile = FileUtils.compressDir(targetDir, zipFile);
        assertNotNull(zipFile);
    }

    public void testCompressFile() {
        File targetFile = new File(EASY_DIR_PATH + File.separator + "common.txt");
        File zipFile = new File(EASY_DIR_PATH + File.separator + COMMON_FILE_ZIP_NAME);
        zipFile = FileUtils.compressFile(targetFile, zipFile);
        assertNotNull(zipFile);
    }

    public void testUncompressFile() {
        File zipFile = new File(EASY_DIR_PATH + File.separator + COMMON_FILE_ZIP_NAME);
        File targetDir = new File(EASY_DIR_PATH);
        FileUtils.uncompressFile(zipFile, targetDir);
    }

}
