package com.wsmt.middleware.service;

import com.wsmt.middleware.endpoints.FileInformation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFileService extends Remote {
    void indexFilesUnderPath(String path) throws RemoteException, Exception;
    List<FileInformation> retrieveFileInformation() throws RemoteException;
    public List<FileInformation> filterFileInfo(String name, String md5, String sha1, String bytes) throws RemoteException;
}
