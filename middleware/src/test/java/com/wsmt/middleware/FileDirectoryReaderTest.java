package com.wsmt.middleware;


import com.wsmt.middleware.service.DirectoryFileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileDirectoryReaderTest {

    @Autowired
    private DirectoryFileReader directoryFileReader;

    @Test
    public void testFileReaderImplementation(){
        directoryFileReader.readFilesUnderRoot("../root");
    }
}
