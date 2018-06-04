package com.wsmt.middleware.endpoints;

import java.io.Serializable;


public class FileInformation implements Serializable {

    private static final long serialVersionUID = 2089757820139622410L;
    private String path;
    private String name;
    private String fileExtension;
    private String MD5_hash;
    private String SHA1_hash;
    private String fileSize;

    public String getFullFilePath() throws Exception {
        if (this.getPath() == null || this.getName() == null || this.getFileExtension()== null){
            throw new Exception("invalid file paths");
        }
        return this.getPath() + "\\" + this.getName() + "." + this.getFileExtension();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getMD5_hash() {
        return MD5_hash;
    }

    public void setMD5_hash(String MD5_hash) {
        this.MD5_hash = MD5_hash;
    }

    public String getSHA1_hash() {
        return SHA1_hash;
    }

    public void setSHA1_hash(String SHA1_hash) {
        this.SHA1_hash = SHA1_hash;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "FileInformation{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", MD5_hash='" + MD5_hash + '\'' +
                ", SHA1_hash='" + SHA1_hash + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}