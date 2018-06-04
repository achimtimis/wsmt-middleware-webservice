package com.wsmt.middleware.endpoints;

import com.wsmt.middleware.endpoints.file.IFileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileServiceBean {

    @Autowired
    private IFileService fileService;

    public void indexFilesUnderPath(String path) throws Exception{
        fileService.indexFilesUnderPath(path);
    }
    public List<FileInformation> retrieveFileInformation(){
        return fileService.retrieveFileInformation();
    }
    public List<FileInformation> filterFileInfo(String name, String md5, String sha1, String bytes){
        return fileService.filterFileInfo(name, md5, sha1, bytes);
    }
}
