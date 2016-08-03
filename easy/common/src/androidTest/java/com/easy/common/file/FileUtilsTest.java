package com.easy.common.file;

import android.os.Environment;
import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

import java.io.File;
import java.util.ArrayList;

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
        File zipFile = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_ZIP_NAME);
        File targetDir = new File(EASY_DIR_PATH);
        targetDir = FileUtils.uncompressFile(zipFile, targetDir);
        assertNotNull(targetDir);
    }

    public void testGetFileSize() {
        File targetFile = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_ZIP_NAME);
        long size = FileUtils.getFileSize(targetFile);
        LogCatUtils.log(String.valueOf(size));
    }

    public void testFindFileByName() {
        File targetDir = new File(EASY_DIR_PATH);
        String fileName = "bufferedWriter.txt";
        ArrayList<File> list = FileUtils.findFileByName(targetDir, fileName);
        for (int i = 0; list != null && i < list.size(); i++)
            LogCatUtils.log(list.get(i).getAbsolutePath());
    }

    public void testFindFileByPath() {
        File targetDir = new File(EASY_DIR_PATH);
        String filePath = EASY_DIR_PATH + File.separator + COMMON_DIR_NAME + File.separator + "bufferedWriter.txt";
        File file = FileUtils.findFileByPath(targetDir, filePath);
        assertNotNull(file);
        LogCatUtils.log(file.getAbsolutePath());
    }

    public void testDeleteFilesExceptName() {
        File targetDir = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_NAME);
        FileUtils.deleteFilesExceptName(targetDir, "bufferedWriter.txt");
    }

    public void testDeleteFilesExceptPath() {
        File targetDir = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_NAME);
        String exceptPath = EASY_DIR_PATH + File.separator + COMMON_DIR_NAME + File.separator + "bufferedWriter.txt";
        FileUtils.deleteFilesExceptPath(targetDir, exceptPath);
    }

    public void testRename() {
        File oldFile = new File(EASY_DIR_PATH + File.separator + "common.txt");
        File newFile = new File(EASY_DIR_PATH + File.separator + COMMON_DIR_NAME + File.separator + "new-common.txt");
        boolean flag = FileUtils.rename(oldFile, newFile);
        assertEquals(true, flag);
    }

}
