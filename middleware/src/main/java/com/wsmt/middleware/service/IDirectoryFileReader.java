package com.wsmt.middleware.service;

import com.wsmt.middleware.endpoints.FileInformation;

import java.util.List;

public interface IDirectoryFileReader {

    List<FileInformation> readFilesUnderRoot(String rootPah);
}
