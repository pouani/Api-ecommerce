package com.stage.ecommerce.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.stage.ecommerce.services.IFlickrPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;

public class FlickrPhotoService implements IFlickrPhotoService {

    @Value("${flickr.apiKey}")
    private String apiKey;
    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;
    @Value("${flickr.appSecret}")
    private String appSecret;


    private Flickr flickr;

    @Autowired
    public FlickrPhotoService(Flickr flickr) {
        this.flickr = flickr;
    }

    @Override
    public String SavePhoto(InputStream photo, String title) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }
}
