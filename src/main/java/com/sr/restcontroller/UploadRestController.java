package com.sr.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sr.service.upload.FileUploadService;

@RestController
@RequestMapping("/api/v1")
public class UploadRestController {
	
	private FileUploadService fileUploadService;
	
	@Autowired
	public UploadRestController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}
	
	@PostMapping("/uploads")
	public String uploadFile(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		return fileUploadService.upload(file);
	}

}
