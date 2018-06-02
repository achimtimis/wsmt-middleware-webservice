package com.wsmt.middleware.service;

import com.wsmt.middleware.domain.FileEntity;
import com.wsmt.middleware.endpoints.FileInformation;
import com.wsmt.middleware.repository.IFileRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private IFileRepositoryDao fileRepository;

    @Autowired
    private IDirectoryFileReader directoryFileReader;


    public void indexFilesUnderPath(String path) throws Exception {
        checkFilePathExists(path); //fail early
        List<FileInformation> fileInformationList = directoryFileReader.readFilesUnderRoot(path);
        if (!fileInformationList.isEmpty()) {
            // map pojo to entity
            List<FileEntity> fileEntities = new ArrayList<>();
            fileRepository.saveAll(fileEntities);
        }

    }

    private void checkFilePathExists(String path) throws Exception {
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
        } else {
            throw new Exception("File path is invalid");
        }
    }
}
