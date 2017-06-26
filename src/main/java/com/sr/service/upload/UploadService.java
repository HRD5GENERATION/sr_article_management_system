package com.sr.service.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	public String upload(MultipartFile file){
		
		String uploadPath = "/opt/images/";
		
		File path = new File(uploadPath);
		if(!path.exists())
			path.mkdirs();
		
		//generate random file name
		String fileName = file.getOriginalFilename();
		fileName = UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(fileName);
		try {	
			//upload file to server
			Files.copy(file.getInputStream(), Paths.get(uploadPath, fileName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return "/resources/images/" + fileName;
	}
}
