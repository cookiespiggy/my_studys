package cn.itcast.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class Mp4Utils {
    private static final String mp4dir = "D:\\BaiduNetdiskDownload\\理论+实战 构建完整JVM知识体系";

    public static void main(String[] args) throws IOException {

        Path FromPath = Paths.get(mp4dir);

        Path toPath = Paths.get("C:\\Users\\yueshow\\Desktop\\todo");

        filesCopy(FromPath, toPath);
    }

    public static void filesCopy(Path from, Path to) throws IOException {

        if (Files.notExists(from)) {
            System.out.println("源文件夹不存在");
        }
        if (Files.notExists(to)) {
            Files.createDirectories(to);
        }
        // walkFileTree()包含用于递归遍历目录树的方法，Path对象指向需要遍历的目录，FileVisitor在便利的时候调用
        Files.walkFileTree(from, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs) throws IOException {
//                Path to1 = to.resolve(from.relativize(file));
//                // 如果说父路径不存在，则创建
//                if (Files.notExists(to1.getParent())) {
//                    Files.createDirectories(to1.getParent());
//                }
                // [天下无鱼][shikey.com]1-2 课程介绍及学习指导.mp4
               // Path fileName = file.getFileName();
                String s = file.getFileName().toString();
                String substring = s.substring(18);
                Path fileName = Paths.get(substring);
                Path to1 = to.resolve(fileName);

                Files.copy(file, to1);
                return FileVisitResult.CONTINUE;//递归遍历文件，空文件无法复制
            }
        });


        System.out.println("Copy成功");
    }
}
