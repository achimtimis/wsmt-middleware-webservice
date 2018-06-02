package com.wsmt.middleware.repository;

import com.wsmt.middleware.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileRepositoryDao extends JpaRepository<FileEntity, Long> {
}
