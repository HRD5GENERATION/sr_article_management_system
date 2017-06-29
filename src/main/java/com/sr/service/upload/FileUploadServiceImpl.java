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
public class FileUploadServiceImpl implements FileUploadService{
	
	private String SERVER_PATH = "/opt/images/";
	private String CLIENT_PATH = "/resources/images/";
	
	@Override
	public String upload(MultipartFile file) {
		return this.singleFileUpload(file, null);
	}

	@Override
	public List<String> upload(List<MultipartFile> files) {
		return this.multipleFileUpload(files, null);
	}

	@Override
	public String upload(MultipartFile file, String folder) {
		return this.singleFileUpload(file, folder);
	}

	@Override
	public List<String> upload(List<MultipartFile> files, String folder) {
		return this.multipleFileUpload(files, folder);
	}
	
	@Override
	public String uploadAndReplace(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> uploadAndReplace(List<MultipartFile> files) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Description("Signle File Upload")
	private String singleFileUpload(MultipartFile file, String folder){
		if(file==null){
			System.out.println("File is not present. Please choose file to upload!");
			return null;
		}
		if(folder != "" && folder != null){
			this.SERVER_PATH += folder;
			this.CLIENT_PATH += folder;
		}
		File path = new File(this.SERVER_PATH);
		if(!path.exists())
			path.mkdirs();
		
		//TODO: Generate random file name
		String filename = file.getOriginalFilename();
		filename = UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1);
		try {	
			//TODO: Upload file to server
			Files.copy(file.getInputStream(), Paths.get(this.SERVER_PATH, filename));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return this.CLIENT_PATH + filename;
	}
	
	@Description("Multiple File Upload")
	private List<String> multipleFileUpload(List<MultipartFile> files, String folder){
		List<String> filenames = new ArrayList<>();
		files.forEach(file->{
			filenames.add(this.singleFileUpload(file, folder));
		});
		return filenames;
	}

}
