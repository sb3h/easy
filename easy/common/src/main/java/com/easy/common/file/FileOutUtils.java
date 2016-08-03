package com.easy.common.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutUtils {

    /**
     * 写文件
     *
     * @param targetFile    目标文件
     * @param append        true为新内容追加到末尾，false为新内容覆盖掉旧内容
     * @param isAutoNewLine true为自动添加换行符，false为不自动添加
     * @param array         新内容
     */
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

}
