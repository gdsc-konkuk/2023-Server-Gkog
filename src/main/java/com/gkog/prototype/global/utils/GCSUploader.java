package com.gkog.prototype.global.utils;

import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class GCSUploader {

    @Value("${spring.cloud.gcp.storage.prefixUrl}")
    private String imageUrlPrefix;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Autowired
    private Storage storage;

    public String uploadImage(MultipartFile imageFile) throws IOException {

        String fileName = generateFileName(imageFile);

        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, fileName)
                        .setContentType(imageFile.getContentType())
                        .build(),
                imageFile.getInputStream()
        );
        return processImageUrl(fileName);
    }

    public void deleteImage(String fileName) {

        Blob blob = storage.get(imageUrlPrefix, fileName);
        if(blob == null) {
            return;
        }

        Storage.BlobSourceOption preCondition = Storage.BlobSourceOption.generationMatch(blob.getGeneration());

        storage.delete(imageUrlPrefix, fileName, preCondition);
        log.info("Image " + fileName + " has been deleted from " + imageUrlPrefix);
    }

    public String generateFileName(MultipartFile imageFile) {
        String originalName = imageFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        return uuid + "_" + originalName;
    }

    public String processImageUrl(String fileName) {
        return imageUrlPrefix + bucketName +"/" + fileName;
    }
}
