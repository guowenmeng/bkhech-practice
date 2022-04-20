package com.bkhech.home.practice.utils;

import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Zip util
 *
 * @author guowm
 * @date 2022/4/12
 */
public class ZipUtil {
    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * 压缩成ZIP 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param zipFilePathName  压缩文件输出地址与名字
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, String zipFilePathName, boolean KeepDirStructure)
            throws RuntimeException {
        File file = new File(zipFilePathName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }

        try (FileOutputStream out = new FileOutputStream(zipFilePathName);
             ZipOutputStream zos = new ZipOutputStream(out)) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtil", e);
        }
    }

    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles        需要压缩的文件列表
     * @param zipFilePathName 压缩文件输出地址与名字
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles, String zipFilePathName) throws RuntimeException, FileNotFoundException {
        File file = new File(zipFilePathName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(zipFilePathName);
             ZipOutputStream zos = new ZipOutputStream(out)) {
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtil", e);
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);

            }

            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String gamePackage = "dino";
        final Pattern pattern = Pattern.compile("^" + gamePackage + "\\.(\\d)$");

        File iniFile = new File("C:\\Users\\guowe\\Downloads\\dino\\dino.ini");
        File tempFile = File.createTempFile(iniFile.getName(), null);
        try {
            Files.move(iniFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            //可做业务处理

            tempFile.renameTo(iniFile);
        } finally {
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }

        File combineStage = new File("C:\\Users\\guowe\\Downloads\\dino\\combine_stage_v2.zip");
        deleteZipEntry(combineStage, pattern);

        ZipUtil.toZip(
                "C:\\Users\\guowe\\Downloads\\dino",
                "C:\\Users\\guowe\\Downloads\\dino-test.zip",
                true
        );
    }

    public static void deleteZipEntry(File zipFile, Pattern pattern) throws IOException {
        File tempFile = File.createTempFile(zipFile.getName(), null);
        Files.move(zipFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        byte[] buf = new byte[1024];
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
             ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile))) {
            ZipEntry entry = zin.getNextEntry();
            while (entry != null) {
                String name = entry.getName();
                Matcher matcher = pattern.matcher(name);
                if (!matcher.matches()) {
                    zout.putNextEntry(new ZipEntry(name));
                    int len;
                    while ((len = zin.read(buf)) > 0) {
                        zout.write(buf, 0, len);
                    }
                }
                entry = zin.getNextEntry();
            }
        } finally {
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}

