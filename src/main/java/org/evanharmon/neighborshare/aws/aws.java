package org.evanharmon.neighborshare.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class aws {

    AWSCredentials credentials = new BasicAWSCredentials(
            "AKIATU2357F7RM2KXO4Q",
            "h/sTJdSMKVzQcJJVthiq7WvO2q4qZwMXpKf5IJDF"
    );

    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_WEST_2)
            .build();
}
