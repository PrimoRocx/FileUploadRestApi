package com.primo.fileUploadRestApi.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface StorageService {

    public String uploadIage(MultipartFile file) throws IOException;

    public byte[] downloadImage(String fileName);
}
