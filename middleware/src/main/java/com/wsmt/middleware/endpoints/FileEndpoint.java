package com.wsmt.middleware.endpoints;

import com.wsmt.middleware.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileEndpoint {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/webservice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void indexFilesUnderPath(@RequestParam("path") String path) throws Exception {
        fileService.indexFilesUnderPath(path);
    }
}
