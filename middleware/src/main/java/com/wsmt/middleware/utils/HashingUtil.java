package com.wsmt.middleware.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {


    private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        ;

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }

    public static String getMD5Hash(String filePath) {
        //Create checksum for this file
        File file = new File(filePath);

        //Use MD5 algorithm
        MessageDigest md5Digest = null;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Get the checksum
        String checksum = null;
        try {
            checksum = getFileChecksum(md5Digest, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //see checksum
        System.out.println(checksum);
        return checksum;
    }

    public static String getSHA1Hash(String filePath) {
        File file = new File(filePath);

        //Use SHA-1 algorithm
        MessageDigest shaDigest = null;
        try {
            shaDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //SHA-1 checksum
        String shaChecksum = null;
        try {
            shaChecksum = getFileChecksum(shaDigest, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shaChecksum;
    }
}
