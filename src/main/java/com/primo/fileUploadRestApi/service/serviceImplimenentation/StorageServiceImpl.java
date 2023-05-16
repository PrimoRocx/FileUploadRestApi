package com.primo.fileUploadRestApi.service.serviceImplimenentation;


import com.primo.fileUploadRestApi.entity.ImageData;
import com.primo.fileUploadRestApi.repository.StorageRepository;
import com.primo.fileUploadRestApi.service.StorageService;
import com.primo.fileUploadRestApi.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadIage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder().name(file.getOriginalFilename()).type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null){
            return "file uploaded successfuly : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbimageDate = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbimageDate.get().getImageData());
        return images;
    }
}
