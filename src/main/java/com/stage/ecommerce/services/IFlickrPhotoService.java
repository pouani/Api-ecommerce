package com.stage.ecommerce.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface IFlickrPhotoService {

    String SavePhoto(InputStream photo, String title) throws FlickrException;
}
