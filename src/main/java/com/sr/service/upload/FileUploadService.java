package com.sr.service.upload;

import java.util.List;

import org.springframework.context.annotation.Description;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	@Description("Upload single file.")
	String upload(MultipartFile file);
	
	@Description("Upload single file to specific folder.")
	String upload(MultipartFile file, String folder);
	
	@Description("Upload multiple files.")
	List<String> upload(List<MultipartFile> files);
	
	@Description("Upload multiple files to specific folder.")
	List<String> upload(List<MultipartFile> files, String folder);

	@Description("Upload and replace single file.")
	String uploadAndReplace(MultipartFile file);

	@Description("Upload and replace multiple files.")
	List<String> uploadAndReplace(List<MultipartFile> files);
	
}
