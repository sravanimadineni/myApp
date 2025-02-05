//package com.media.service;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.S3Exception;
//
//import java.io.File;
//import java.net.URL;
//
//@Service
//public class S3Service {
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucketName;
//
//    @Value("${cloud.aws.region}")
//    private String region;
//
//    private final S3Client s3Client;
//
//    public S3Service() {
//        this.s3Client = S3Client.builder()
//                .region(Region.of(region))
//                .credentialsProvider(ProfileCredentialsProvider.create())
//                .build();
//    }
//
//    public String uploadFile(String fileName, File file) {
//        try {
//            s3Client.putObject(PutObjectRequest.builder()
//                            .bucket(bucketName)
//                            .key(fileName)
//                            .build(),
//                    software.amazon.awssdk.core.sync.RequestBody.fromFile(file));
//            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
//        } catch (S3Exception e) {
//            throw new RuntimeException("Failed to upload file to S3: " + e.getMessage());
//        }
//    }
//}
