package com.primo.fileUploadRestApi;

import com.primo.fileUploadRestApi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class FileUploadRestApiApplication {

	@Autowired
	private StorageService service;

	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
	String uploadImage = service.uploadIage(file);
	return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData = service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}

	public static void main(String[] args) {
		SpringApplication.run(FileUploadRestApiApplication.class, args);
	}

}