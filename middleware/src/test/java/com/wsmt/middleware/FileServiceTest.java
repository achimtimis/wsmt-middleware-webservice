package com.wsmt.middleware;

import com.wsmt.middleware.domain.FileEntity;
import com.wsmt.middleware.repository.IFileRepositoryDao;
import com.wsmt.middleware.service.FileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Autowired
    private IFileRepositoryDao fileRepositoryDao;

    private List<FileEntity> files;

    @Before
    public void setUp(){
        FileEntity fileEntity1 = FileEntity.builder().name("x").fileExtension("txt").fileSize("12").md5Hash("xxx").sha1Hash("xxx").build();
        FileEntity fileEntity2 = FileEntity.builder().name("y").fileExtension("txt").fileSize("13").md5Hash("yyy").sha1Hash("yyy").build();
        FileEntity fileEntity3 = FileEntity.builder().name("zx").fileExtension("txt").fileSize("13").md5Hash("zx").sha1Hash("zx").build();
        files = Arrays.asList(fileEntity1, fileEntity2, fileEntity3);
        fileRepositoryDao.saveAll(files);
        }

    @After
    public void tearDown(){
        fileRepositoryDao.deleteAll(files);
    }

    @Test
    @Transactional
    public void testFiltering(){
        Assert.assertEquals(2, fileService.filterFileInfo( "x", "x", "x", "").size());
        Assert.assertEquals(1, fileService.filterFileInfo("y", "y", "y", "").size());
        Assert.assertEquals(1, fileService.filterFileInfo("y", "y", "yyy", "").size());
        Assert.assertEquals(0, fileService.filterFileInfo("zx", "nonexisting", "", "").size());
        Assert.assertEquals(1, fileService.filterFileInfo("zx", "zx", "zx", "").size());
        Assert.assertEquals(3, fileService.filterFileInfo("", "", "", "").size());
    }
}
