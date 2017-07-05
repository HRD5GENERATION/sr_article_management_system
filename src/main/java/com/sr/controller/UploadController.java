package com.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sr.service.upload.FileUploadService;

@Controller
public class UploadController {

	/*@PostMapping("/file/upload")
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		
		String uploadPath = request.getServletContext().getRealPath("resources/");
		System.out.println(uploadPath);
		try {
			Files.copy(file.getInputStream(), Paths.get(uploadPath, file.getOriginalFilename()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return "fileupload";
	}*/
	
	/*@PostMapping("/file/upload")
	public String upload(@RequestParam("file") MultipartFile file){
		
		String uploadPath = "/opt/images/";
		File path = new File(uploadPath);
		if(!path.exists())
			path.mkdirs();
		
		System.out.println(uploadPath);
		try {
			Files.copy(file.getInputStream(), Paths.get(uploadPath, file.getOriginalFilename()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return "fileupload";
	}*/
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/file/upload")
	public String upload(@RequestParam("file") List<MultipartFile> files){
		
		System.out.println(fileUploadService.upload(files));
		
		/*String uploadPath = "/opt/images/";
		File path = new File(uploadPath);
		if(!path.exists())
			path.mkdirs();
		
		for(MultipartFile file: files){
			try {
				//generate random file name
				String fileName = file.getOriginalFilename();
				fileName = UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				System.out.println(fileName);
				
				//upload file to server
				Files.copy(file.getInputStream(), Paths.get(uploadPath, fileName));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}*/
		return "fileupload";
	}
}
