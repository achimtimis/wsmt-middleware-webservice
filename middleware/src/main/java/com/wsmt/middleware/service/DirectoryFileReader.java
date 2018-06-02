package com.wsmt.middleware.service;


import com.wsmt.middleware.endpoints.FileInformation;
import com.wsmt.middleware.utils.HashingUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DirectoryFileReader implements IDirectoryFileReader {

    private List<FileInformation> fileInformationList;

    @Override
    public List<FileInformation> readFilesUnderRoot(String rootPath) {
        fileInformationList = new ArrayList<>();
        try {
            listFilesForFolder(new File(rootPath));
        } catch (Exception e) {
            //
        }
        return fileInformationList;
    }

    private void listFilesForFolder(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (fileEntry.isFile()) {
                    addFileInformation(fileEntry);
                }
            }
        }
    }

    private void addFileInformation(File fileEntry) {
        FileInformation fileInfo = FileInformation.builder().build();
        fileInfo.setName(fileEntry.getName().substring(0, fileEntry.getName().lastIndexOf(".")));
        fileInfo.setFileExtension(fileEntry.getName().substring(fileEntry.getName().lastIndexOf(".") + 1,
                fileEntry.getName().length()));
        fileInfo.setFileSize(String.valueOf(fileEntry.length()));
        try {
            fileInfo.setPath(fileEntry.getCanonicalFile().toString().substring(0,
                    fileEntry.getCanonicalFile().toString().lastIndexOf("\\") + 1));
        } catch (IOException e) {
            e.printStackTrace();
            fileInfo.setPath("invalid path");
        }
        fileInfo.setMD5_hash(HashingUtil.getMD5Hash(fileEntry.getPath()));
        fileInfo.setSHA1_hash(HashingUtil.getSHA1Hash(fileEntry.getPath()));
        fileInformationList.add(fileInfo);

    }


}
