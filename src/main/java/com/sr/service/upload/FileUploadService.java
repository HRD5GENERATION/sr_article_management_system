package com.sr.service.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	private String serverPath = "/article-management-system/images/";
	private String clientPath = "/resources/images/";
	
	@Description("Upload single file")
	public String upload(MultipartFile file){	
		if(file==null){
			System.out.println("File is not present!");
			return null;
		}
		File path = new File(this.serverPath);
		if(!path.exists())
			path.mkdirs();
		
		//Generate random file name
		String filename = file.getOriginalFilename();
		filename = UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1);
		try {	
			//Upload file to server
			Files.copy(file.getInputStream(), Paths.get(this.serverPath, filename));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return this.clientPath + filename;
	}
	
	@Description("Upload multiple files")
	public List<String> upload(List<MultipartFile> files){
		List<String> filenames = new ArrayList<>();
		for(MultipartFile file:files){
			filenames.add(this.upload(file));
		}
		return filenames;
	}
}
