package com.rawat.todo.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
class fileController{

    Logger logger  = LoggerFactory.getLogger(fileController.class);
    @PostMapping("/singleUpload")
    public String fileHandler(@RequestParam("image") MultipartFile file){
        logger.info("File Name: "+file.getName());
        // Formating File Size.
        DecimalFormat db =new DecimalFormat("#.##");
        logger.info("File Size: "+db.format(file.getSize()/(1024.0))+" KB");

        logger.info("File Content Type: "+file.getContentType());
        logger.info("File Real Name: "+file.getOriginalFilename());

        return "Files Received";
    }
    @PostMapping("/multipleFiles")
    public String multipleFileHandler(@RequestParam("images") MultipartFile[] files){

        Arrays.stream(files).forEach(file->{
            logger.info("File Name: "+file.getOriginalFilename());
            DecimalFormat db = new DecimalFormat("#.##");
            logger.info("File Size: "+db.format(file.getSize()/1024.0)+" KB");
        });

        return "Files Received";
    }

    // Servicing images fiels in Response.

    @GetMapping("/serve-image")
    public ResponseEntity<String> serveImageHandler(HttpServletResponse response){
        try {
            InputStream fileInputStream = new FileInputStream("images/temp.png");
            // Setting file response Type
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            // Sending file as response stream;
            // Copying Stream of fileInputStream{Where our file is} to response outputStream.
            StreamUtils.copy(fileInputStream,response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Files Send", HttpStatus.CREATED);
    }



}
