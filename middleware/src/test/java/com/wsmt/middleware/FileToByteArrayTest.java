package com.wsmt.middleware;

import com.wsmt.middleware.utils.FileToByteArray;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class FileToByteArrayTest {

    @Test
    public void testFileToByteArray() {

        File file = new File("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file1.txt");
        try {
            byte[] bytes = FileToByteArray.readBytes(file);
            Assert.assertNotNull(bytes);
            Assert.assertEquals(new String(bytes, StandardCharsets.UTF_8), "123");
        } catch (Exception e) {
            Assert.fail("should not be the case");
        }
    }
}
