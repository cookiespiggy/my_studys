package com.luckyDL.ftpcameldemo;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.zip.ZipEntry;

public class FTPServer {

    @Test
    public void ftpServer() throws IOException {
        FakeFtpServer fakeFtpServer = new FakeFtpServer();
        fakeFtpServer.addUserAccount(new UserAccount("ftpuser", "ftp2018", "/data"));
        FileSystem fileSystem = new UnixFakeFileSystem();
        fileSystem.add(new DirectoryEntry("/data"));
        fileSystem.add(new FileEntry("/data/foobar.txt", "abcdef 1234567890"));

        byte[] bytes = Files.readAllBytes(Paths.get("D:\\apache-maven-3.6.0-bin.zip"));
        FileEntry fileEntry = new FileEntry("/data/maven.zip");
        fileEntry.setContents(bytes);
        fileSystem.add(fileEntry);


        fakeFtpServer.setFileSystem(fileSystem);
        fakeFtpServer.setServerControlPort(9765); // not 0
        fakeFtpServer.start();

        System.in.read();
    }



}
