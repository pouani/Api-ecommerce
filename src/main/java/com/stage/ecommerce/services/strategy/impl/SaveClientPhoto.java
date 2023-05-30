package com.stage.ecommerce.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import com.stage.ecommerce.dto.ClientDto;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidOperationException;
import com.stage.ecommerce.services.IClientService;
import com.stage.ecommerce.services.IFlickrPhotoService;
import com.stage.ecommerce.services.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto> {

    private IFlickrPhotoService flickrPhotoService;
    private IClientService clientService;

    @Autowired
    public SaveClientPhoto(IFlickrPhotoService flickrPhotoService, IClientService clientService) {
        this.flickrPhotoService = flickrPhotoService;
        this.clientService = clientService;
    }

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {

        ClientDto client = clientService.findById(id);
        String urlPhoto = flickrPhotoService.savePhoto(photo, titre);

        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo du client ", ErrorCodes.PHOTO_EXCEPTION);
        }

        client.setPhoto(urlPhoto);
        return clientService.save(client);
    }
}
