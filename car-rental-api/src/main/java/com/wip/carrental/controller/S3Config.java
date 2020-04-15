/*
package com.wip.carrental.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	
   // @Value("${amazonProperties.accessKey}")

   //final String accessKey = "carefull";
   final String accessKey = "AKIAIHV2C3OCOLRQCL4A";
    //@Value("${amazonProperties.secretKey}")

   //final String secretKey = "carefull" ;
   final String secretKey = "aC+1e5fKENIEgJN8Y/UgoEU2wPjYa++thzho+StQ";

   
    
    @Bean
    public  AmazonS3Client amazonS3Client() {
    BasicAWSCredentials creds = new BasicAWSCredentials(this.accessKey, this.secretKey);
    
    AmazonS3Client  s3Client = (AmazonS3Client)AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).build();
       return s3Client;
    }
    
}
*/
