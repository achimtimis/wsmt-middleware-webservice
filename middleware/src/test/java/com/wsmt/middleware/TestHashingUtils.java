package com.wsmt.middleware;

import com.wsmt.middleware.utils.HashingUtil;
import org.junit.Assert;
import org.junit.Test;

public class TestHashingUtils {


    @Test
    public void testMD5EqualFileContents() {

        String md5HashFile1 = HashingUtil.getMD5Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file1.txt");
        String md5HashFile2 = HashingUtil.getMD5Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file2EqualToFile1.txt");
        Assert.assertEquals(md5HashFile1, md5HashFile2);
    }

    @Test
    public void testSha1EqualFileContents() {

        String sha1HashFile1 = HashingUtil.getSHA1Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file1.txt");
        String sha1HashFle2 = HashingUtil.getSHA1Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file2EqualToFile1.txt");
        Assert.assertEquals(sha1HashFile1, sha1HashFle2);
    }


    @Test
    public void testDifferentFileContentHashMD5(){
        String md5HashFile1 = HashingUtil.getSHA1Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file1.txt");
        String md5HashFile2 = HashingUtil.getSHA1Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file3DifferentThanFile1.txt");
        Assert.assertNotEquals(md5HashFile1, md5HashFile2);
    }

    @Test
    public void testDifferentFileContentHashSha1(){
        String sha1HashFile1 = HashingUtil.getSHA1Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file1.txt");
        String sha1HashFle2 = HashingUtil.getSHA1Hash("D:\\github\\wsmt-middleware\\middleware\\src\\test\\resources\\file3DifferentThanFile1.txt");
        Assert.assertNotEquals(sha1HashFile1, sha1HashFle2);
    }
}
