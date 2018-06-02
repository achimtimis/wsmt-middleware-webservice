package com.wsmt.middleware.endpoints;

import com.wsmt.middleware.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FileEndpoint {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/webservice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void indexFilesUnderPath(@RequestParam("path") String path) throws Exception {
        fileService.indexFilesUnderPath(path);
    }

    @RequestMapping(value = "/webservice/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FileInformation> retrieveFileInformation(){
        return fileService.retrieveFileInformation();
    }

    @RequestMapping(value = "/webservice/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FileInformation> filterFileInformation(@RequestParam("name") Optional<String> name, @RequestParam("md5") Optional<String> md5,
                                                       @RequestParam("sha1") Optional<String> sha1, @RequestParam("bytes") Optional<String> bytes)
    {
        String fileName = name.orElse("");
        String md5Value = md5.orElse("");
        String sha1Value = sha1.orElse("");
        String bytesValue = bytes.orElse("");
        return fileService.filterFileInfo(fileName, md5Value, sha1Value, bytesValue);
    }
}
