package com.wip.carrental.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api")
public class S3StorageController {
    @Autowired
    S3Factory s3Factory;

     @GetMapping(path = "/buckets")
    public List<com.amazonaws.services.s3.model.Bucket> listBuckets(){
        return s3Factory.getAllBuckets();
    }
 

    @PostMapping(path = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public Map<String,String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
        s3Factory.uploadFile(files.getOriginalFilename(),files.getBytes());
        Map<String,String> result = new HashMap<>();
        result.put("key",files.getOriginalFilename());
        return result;
    }

    @GetMapping(path = "/download")
    public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
        byte[] data = s3Factory.getFile(file);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
                .body(resource);

    }
}