package com.stage.ecommerce.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import com.stage.ecommerce.dto.UtilisateurDto;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidOperationException;
import com.stage.ecommerce.services.IFlickrPhotoService;
import com.stage.ecommerce.services.IUtilisateurService;
import com.stage.ecommerce.services.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {

    private IFlickrPhotoService flickrPhotoService;
    private IUtilisateurService utilisateurService;
    @Autowired
    public SaveUtilisateurPhoto(IFlickrPhotoService flickrPhotoService, IUtilisateurService utilisateurService) {
        this.flickrPhotoService = flickrPhotoService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {

        UtilisateurDto utilisateur = utilisateurService.findById(id);
        String urlPhoto = flickrPhotoService.savePhoto(photo, titre);

        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'utilisateur ", ErrorCodes.PHOTO_EXCEPTION);
        }

        utilisateur.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateur);
    }
}
