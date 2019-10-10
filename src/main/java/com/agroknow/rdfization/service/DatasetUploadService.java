package com.agroknow.rdfization.service;

import com.agroknow.rdfization.properties.UploadServerProperties;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.InetAddress;

@Service
public class DatasetUploadService {

    @Autowired
    private UploadServerProperties properties;

    @Autowired
    private DatasetImportService importService;

    public void upload(String filePath, boolean test) throws Exception {

        JSch jsch = new JSch();
        Session session = null;

        session = jsch.getSession(properties.getUsername(), properties.getHost(), 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(properties.getPassword());
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;

        FileInputStream fis = new FileInputStream(new File(filePath));

        sftpChannel.put(fis, properties.getDirectory() + FilenameUtils.getName(filePath));
        sftpChannel.exit();
        session.disconnect();

        importService.importDataset(FilenameUtils.getName(filePath), test);
    }
}
