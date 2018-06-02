package com.wsmt.middleware.repository;

import com.wsmt.middleware.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFileRepositoryDao extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByNameContainingAndMd5HashContainingAndSha1HashContaining(String name, String MD5_hash, String SHA1_hash);
    List<FileEntity> findAllByMd5Hash(String md5Hash);
    List<FileEntity> findAllBySha1Hash(String sha1Hash);
}
