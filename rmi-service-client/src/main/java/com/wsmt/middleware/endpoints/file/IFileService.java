package com.wsmt.middleware.endpoints.file;

import com.wsmt.middleware.endpoints.FileInformation;

import java.util.List;

public interface IFileService {
    void indexFilesUnderPath(String path) throws Exception;

    List<FileInformation> retrieveFileInformation();

    public List<FileInformation> filterFileInfo(String name, String md5, String sha1, String bytes);
}
