package com.wsmt.middleware.service;

import com.wsmt.middleware.domain.FileEntity;
import com.wsmt.middleware.endpoints.FileInformation;
import com.wsmt.middleware.repository.IFileRepositoryDao;
import com.wsmt.middleware.utils.FileToByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.rmi.PortableRemoteObject;
import java.io.File;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileService extends PortableRemoteObject implements IFileService {

    @Autowired
    private IFileRepositoryDao fileRepository;

    @Autowired
    private IDirectoryFileReader directoryFileReader;

    public FileService() throws RemoteException {
        super();
    }


    public void indexFilesUnderPath(String path) throws RemoteException, Exception {
        String updatedPath = path.replace("/", "\\");
        checkFilePathExists(updatedPath); //fail early
        List<FileInformation> fileInformationList = directoryFileReader.readFilesUnderRoot(updatedPath);
        if (!fileInformationList.isEmpty()) {
            // map pojo to entity
            List<FileEntity> fileEntities = fileInformationList.stream().map(this::mapModelToEntity).collect(Collectors.toList());
            fileRepository.saveAll(fileEntities);
        }

    }

    private void checkFilePathExists(String path) throws Exception {
        File f = new File(path);
        if (!f.exists()) {
            throw new Exception("File path is invalid");
        }
    }


    private FileEntity mapModelToEntity(FileInformation t) {
        return FileEntity.builder().name(t.getName()).fileExtension(t.getFileExtension())
                .fileSize(t.getFileSize()).md5Hash(t.getMD5_hash()).sha1Hash(t.getSHA1_hash()).path(t.getPath()).build();
    }

    private FileInformation mapEntityToModel(FileEntity t) {
        FileInformation fileInformation = new FileInformation();
        fileInformation.setName(t.getName());
        fileInformation.setSHA1_hash(t.getSha1Hash());
        fileInformation.setMD5_hash(t.getMd5Hash());
        fileInformation.setPath(t.getPath());
        fileInformation.setFileSize(t.getFileSize());
        fileInformation.setFileExtension(t.getFileExtension());
        return fileInformation;
//        return FileInformation.builder().name(t.getName()).fileExtension(t.getFileExtension())
//                .fileSize(t.getFileSize()).MD5_hash(t.getMd5Hash()).SHA1_hash(t.getSha1Hash()).path(t.getPath()).build();

    }

    public List<FileInformation> retrieveFileInformation() throws RemoteException {
        return fileRepository.findAll().stream().map(this::mapEntityToModel).collect(Collectors.toList());
    }

    public List<FileInformation> filterFileInfo(String name, String md5, String sha1, String bytes) throws RemoteException {
        Set<FileInformation> results = new HashSet<>();
//        if (!StringUtils.isEmpty(md5)){
//            results.addAll(fileRepository.findAllByMd5Hash(md5).stream().map(this::mapEntityToModel).collect(Collectors.toList()));
//        } else if (!StringUtils.isEmpty(sha1)){
//            results.addAll(fileRepository.findAllBySha1Hash(sha1).stream().map(this::mapEntityToModel).collect(Collectors.toList()));
//        } else{
        results = fileRepository.findAllByNameContainingAndMd5HashContainingAndSha1HashContaining(
                name, md5, sha1).stream().map(this::mapEntityToModel).collect(Collectors.toSet());
        if (!StringUtils.isEmpty(bytes)) {
            Set<FileInformation> collect = new HashSet<>();
            results.forEach(fileInformation -> {
                try {
                    if (Arrays.toString(FileToByteArray.readBytes(new File(fileInformation.getFullFilePath()))).contains(bytes)) {
                        collect.add(fileInformation);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            results = collect;
        }
        return new ArrayList<>(results);
    }
}
