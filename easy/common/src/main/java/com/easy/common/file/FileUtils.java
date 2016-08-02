package com.easy.common.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    private static final int BUFFER_SIZE = 4096;
    /**
     * 压缩文件成功
     */
    private static final int COMPRESS_FILE_SUCCESS = 1;
    /**
     * 压缩文件失败
     */
    private static final int COMPRESS_FILE_FAIL = 0;

    public static File createDir(File parentDir, String dirName) {
        File dir = new File(parentDir, dirName);
        if (!dir.exists())
            dir.mkdirs();
        return dir;
    }

    public static void bufferedWriter(File targetFile, boolean append, boolean isAutoNewLine, String... array) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(targetFile, append));
            for (int i = 0; array != null && i < array.length; i++) {
                out.write(array[i]);
                if (isAutoNewLine)
                    out.newLine();
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static File compressDir(File targetDir, File zipFile) {
        ZipOutputStream zipOut = null;
        try {
            String zipPath = zipFile.getAbsolutePath();
            zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            int compressStatus = compressDir(targetDir, targetDir.getName(), zipOut, zipPath);
            zipOut.flush();
            if (compressStatus == COMPRESS_FILE_SUCCESS)
                return zipFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipOut != null) {
                try {
                    zipOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        zipFile.delete();
        return null;
    }

    public static File compressFile(File targetFile, File zipFile) {
        ZipOutputStream zipOut = null;
        try {
            if (targetFile.getAbsolutePath().equals(zipFile.getAbsolutePath()) == false) {
                zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
                compressFile(zipOut, targetFile, targetFile.getName());
                zipOut.flush();
                return zipFile;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipOut != null) {
                try {
                    zipOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        zipFile.delete();
        return null;
    }

    public static File uncompressFile(File zipFile, File targetDir) {
        ZipInputStream zipIn = null;
        try {
            int len;
            byte[] buffer = new byte[BUFFER_SIZE];
            String baseDirPath = targetDir.getAbsolutePath();
            ZipFile realZipFile = new ZipFile(zipFile);
            zipIn = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                File file = new File(baseDirPath + File.separator + entry.getName());
                if (file.isDirectory() && !file.exists())
                    file.mkdirs();
                InputStream in = realZipFile.getInputStream(entry);
                FileOutputStream out = new FileOutputStream(file);
                while ((len = in.read(buffer)) != -1)
                    out.write(buffer, 0, len);
                in.close();
                out.close();
            }
            return targetDir;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipIn != null) {
                try {
                    zipIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        targetDir.delete();
        return null;
    }

    public static File findFileByName(File targetDir, String fileName) {
        File targetFile = null;
        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    targetFile = findFileByName(file, fileName);
                    if (targetFile != null) {
                        if (targetFile.getName().equals(fileName))
                            break;
                    }
                } else {
                    if (file.getName().equals(fileName)) {
                        targetFile = file;
                        break;
                    }
                }
            }
        }
        return targetFile;
    }

    public static File findFileByPath(File targetDir, String filePath) {
        File targetFile = null;
        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    targetFile = findFileByPath(file, filePath);
                    if (targetFile != null) {
                        if (targetFile.getAbsolutePath().equals(filePath))
                            break;
                    }
                } else {
                    if (file.getAbsolutePath().equals(filePath)) {
                        targetFile = file;
                        break;
                    }
                }
            }
        }
        return targetFile;
    }

    public static void deleteFilesExceptName(File targetDir, String exceptFileName) {
        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFilesExceptName(file, exceptFileName);
                    if (file.getName().equals(exceptFileName) == false)
                        file.delete();
                } else {
                    if (file.getName().equals(exceptFileName))
                        continue;
                    file.delete();
                }
            }
        }
    }

    public static void deleteFilesExceptPath(File targetDir, String exceptFilePath) {
        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFilesExceptPath(file, exceptFilePath);
                    if (file.getAbsolutePath().equals(exceptFilePath) == false)
                        file.delete();
                } else {
                    if (file.getAbsolutePath().equals(exceptFilePath))
                        continue;
                    file.delete();
                }
            }
        }
    }

    // *********************************************************************************************
    // 私有方法

    private static int compressDir(File targetDir, String baseDirPath, ZipOutputStream zipOut, String zipPath) {
        int compressStatus = COMPRESS_FILE_SUCCESS;
        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (compressStatus == COMPRESS_FILE_FAIL)
                    return compressStatus;
                if (file.getAbsolutePath().equals(zipPath))
                    continue;
                if (file.isDirectory())
                    compressStatus = compressStatus & compressDir(file, (baseDirPath + File.separator + file.getName()), zipOut, zipPath);
                else {
                    try {
                        compressFile(zipOut, file, baseDirPath + File.separator + file.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                        compressStatus = COMPRESS_FILE_FAIL;
                    }
                }
            }
        } else
            compressStatus = COMPRESS_FILE_FAIL;
        return compressStatus;
    }

    private static void compressFile(ZipOutputStream zipOut, File file, String fileName) throws IOException {
        int len;
        byte[] buffer = new byte[BUFFER_SIZE];
        zipOut.putNextEntry(new ZipEntry(fileName));
        FileInputStream in = new FileInputStream(file);
        while ((len = in.read(buffer)) > 0)
            zipOut.write(buffer, 0, len);
        zipOut.closeEntry();
        in.close();
    }

}
