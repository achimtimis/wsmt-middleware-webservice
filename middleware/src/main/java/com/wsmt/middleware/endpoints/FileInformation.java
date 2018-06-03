package com.wsmt.middleware.endpoints;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInformation {
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
}
