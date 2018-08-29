package com.org.df.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.df.utils.MediaTypeUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path="/download")
public class DfController {
	

	
	 private static final String DIRECTORY = "F:/document/Application";
	 
	    @Autowired
	    private ServletContext servletContext;
	 
	    @ApiOperation(value = "")
	    @ApiResponses(value = { @ApiResponse(code = 200, message = "Downloaded sucessfully") })
	    @RequestMapping("/files")
	    public ResponseEntity<InputStreamResource> downloadFile1(
	            @RequestParam(required= true) String fileName) throws IOException {
	 
	        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
	        System.out.println("fileName: " + fileName);
	        System.out.println("mediaType: " + mediaType);
	 
	        File file = new File(DIRECTORY + "/" + fileName);
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	 
	        return ResponseEntity.ok()
	                // Content-Disposition
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
	                // Content-Type
	                .contentType(mediaType)
	                // Contet-Length
	                .contentLength(file.length()) //
	                .body(resource);
	    }
	    
}
