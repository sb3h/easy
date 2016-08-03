package com.easy.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    /**
     * 缓存大小
     */
    private static final int BUFFER_SIZE = 4096;
    /**
     * 压缩文件成功
     */
    private static final int COMPRESS_FILE_SUCCESS = 1;
    /**
     * 压缩文件失败
     */
    private static final int COMPRESS_FILE_FAIL = 0;

    /**
     * 创建新目录
     *
     * @param parentDir 父目录
     * @param dirName   新目录的名称
     * @return 新目录
     */
    public static File createDir(File parentDir, String dirName) {
        File dir = new File(parentDir, dirName);
        if (!dir.exists())
            dir.mkdirs();
        return dir;
    }

    /**
     * 压缩目录
     *
     * @param targetDir 要压缩的目录
     * @param zipFile   生成的压缩文件
     * @return 生成的压缩文件，为null则压缩失败
     */
    public static File compressDir(File targetDir, File zipFile) {
        if (targetDir == null || !targetDir.exists() || !targetDir.isDirectory())
            throw new IllegalArgumentException("targetDir is null or is not exists or is not directory");

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

    /**
     * 压缩文件
     *
     * @param targetFile 要压缩的文件
     * @param zipFile    生成的压缩文件
     * @return 生成的压缩文件，为null则压缩失败
     */
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

    /**
     * 解压缩文件
     *
     * @param zipFile   要被解压的压缩文件
     * @param targetDir 解压到指定目录
     * @return 解压到的指定目录，为null则解压失败
     */
    public static File uncompressFile(File zipFile, File targetDir) {
        if (targetDir == null || !targetDir.exists() || !targetDir.isDirectory())
            throw new IllegalArgumentException("targetDir is null or is not exists or is not directory");

        ZipInputStream zipIn = null;
        try {
            int len;
            byte[] buffer = new byte[BUFFER_SIZE];
            String baseDirPath = targetDir.getAbsolutePath();
            ZipFile realZipFile = new ZipFile(zipFile);
            zipIn = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                String entryName = entry.getName();
                int lastSeparatorIndex = entryName.lastIndexOf(File.separator);
                if (lastSeparatorIndex != -1) {
                    String dirPath = entryName.substring(0, lastSeparatorIndex);
                    File dir = new File(baseDirPath + File.separator + dirPath);
                    if (!dir.exists())
                        dir.mkdirs();
                }

                File file = new File(baseDirPath + File.separator + entryName);
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

    /**
     * 获取文件或目录的大小
     *
     * @param targetFile 要获取大小的文件或目录
     * @return 文件大小
     */
    public static long getFileSize(File targetFile) {
        long totalSize = 0;
        if (targetFile.isDirectory()) {
            File[] files = targetFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        long size = getFileSize(file);
                        totalSize = totalSize + size;
                    } else
                        totalSize = totalSize + file.length();
                }
            }
        } else
            totalSize = targetFile.length();
        return totalSize;
    }

    /**
     * 根据文件名称查找
     *
     * @param targetDir 要查找文件的目录
     * @param fileName  要查找的文件名称
     * @return 目标文件集合，如果集合的size=0则查找不到
     */
    public static ArrayList<File> findFileByName(File targetDir, String fileName) {
        if (targetDir == null || !targetDir.exists() || !targetDir.isDirectory())
            throw new IllegalArgumentException("targetDir is null or is not exists or is not directory");

        ArrayList<File> targetFileList = new ArrayList<File>();
        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.getName().equals(fileName))
                    targetFileList.add(file);
                if (file.isDirectory()) {
                    ArrayList<File> list = findFileByName(file, fileName);
                    if (list != null && !list.isEmpty())
                        targetFileList.addAll(list);
                }
            }
        }
        return targetFileList;
    }

    /**
     * 根据文件路径查找
     *
     * @param targetDir 要查找文件的目录
     * @param filePath  要查找的文件路径
     * @return 目标文件，为null则查找不到
     */
    public static File findFileByPath(File targetDir, String filePath) {
        if (targetDir == null || !targetDir.exists() || !targetDir.isDirectory())
            throw new IllegalArgumentException("targetDir is null or is not exists or is not directory");

        File targetFile = null;
        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.getAbsolutePath().equals(filePath)) {
                    targetFile = file;
                    break;
                }
                if (file.isDirectory()) {
                    targetFile = findFileByPath(file, filePath);
                    if (targetFile != null)
                        break;
                }
            }
        }
        return targetFile;
    }

    /**
     * 除了指定文件名称的文件不能删除外，删除其他全部文件
     *
     * @param targetDir       要删除文件的目录
     * @param exceptFileNames 不能删除的文件名称
     */
    public static void deleteFilesExceptName(File targetDir, String... exceptFileNames) {
        if (targetDir == null || !targetDir.exists() || !targetDir.isDirectory())
            throw new IllegalArgumentException("targetDir is null or is not exists or is not directory");

        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory())
                    deleteFilesExceptName(file, exceptFileNames);
                if (containString(exceptFileNames, file.getName()))
                    continue;
                file.delete();
            }
        }
    }

    /**
     * 除了指定文件路径的文件不能删除外，删除其他全部文件
     *
     * @param targetDir       要删除文件的目录
     * @param exceptFilePaths 不能删除的文件路径
     */
    public static void deleteFilesExceptPath(File targetDir, String... exceptFilePaths) {
        if (targetDir == null || !targetDir.exists() || !targetDir.isDirectory())
            throw new IllegalArgumentException("targetDir is null or is not exists or is not directory");

        File[] files = targetDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory())
                    deleteFilesExceptPath(file, exceptFilePaths);
                if (containString(exceptFilePaths, file.getAbsolutePath()))
                    continue;
                file.delete();
            }
        }
    }

    /**
     * 移动或重命名文件
     *
     * @param oldFile 要被移动或重命名的老文件
     * @param newFile 新文件
     * @return true为此次操作成功，false为失败
     */
    public static boolean rename(File oldFile, File newFile) {
        boolean result = false;
        if (oldFile.exists() &&
                oldFile.getAbsolutePath().equals(newFile.getAbsolutePath()) == false)
            result = oldFile.renameTo(newFile);
        return result;
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

    private static boolean containString(String[] array, String targetStr) {
        boolean result = false;
        for (int i = 0; array != null && i < array.length; i++) {
            if (targetStr.equals(array[i])) {
                result = true;
                break;
            }
        }
        return result;
    }

}
