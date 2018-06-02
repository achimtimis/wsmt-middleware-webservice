package com.wsmt.middleware.service;

import com.wsmt.middleware.domain.FileEntity;
import com.wsmt.middleware.endpoints.FileInformation;
import com.wsmt.middleware.repository.IFileRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private IFileRepositoryDao fileRepository;

    @Autowired
    private IDirectoryFileReader directoryFileReader;


    public void indexFilesUnderPath(String path) throws Exception {
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
        return FileInformation.builder().name(t.getName()).fileExtension(t.getFileExtension())
                .fileSize(t.getFileSize()).MD5_hash(t.getMd5Hash()).SHA1_hash(t.getSha1Hash()).path(t.getPath()).build();

    }

    public List<FileInformation> retrieveFileInformation() {
        return fileRepository.findAll().stream().map(this::mapEntityToModel).collect(Collectors.toList());
    }

    public List<FileInformation> filterFileInfo(String name, String md5, String sha1, String bytes) {
        List<FileInformation> results = new ArrayList<>();
//        if (!StringUtils.isEmpty(md5)){
//            results.addAll(fileRepository.findAllByMd5Hash(md5).stream().map(this::mapEntityToModel).collect(Collectors.toList()));
//        } else if (!StringUtils.isEmpty(sha1)){
//            results.addAll(fileRepository.findAllBySha1Hash(sha1).stream().map(this::mapEntityToModel).collect(Collectors.toList()));
//        } else{
        results = fileRepository.findAllByNameContainingAndMd5HashContainingAndSha1HashContaining(
                name, md5, sha1).stream().map(this::mapEntityToModel).collect(Collectors.toList());

//        }
        return results;
    }
}
