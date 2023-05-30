package com.stage.ecommerce.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import com.stage.ecommerce.dto.ProduitDto;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidOperationException;
import com.stage.ecommerce.services.IFlickrPhotoService;
import com.stage.ecommerce.services.IProduitService;
import com.stage.ecommerce.services.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("produitStrategy")
@Slf4j
public class SaveProduitPhoto implements Strategy<ProduitDto> {

    private IFlickrPhotoService flickrPhotoService;
    private IProduitService produitService;
    @Autowired
    public SaveProduitPhoto(IFlickrPhotoService flickrPhotoService, IProduitService produitService) {
        this.flickrPhotoService = flickrPhotoService;
        this.produitService = produitService;
    }

    @Override
    public ProduitDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {

        ProduitDto produit = produitService.findById(id);
        String urlPhoto = flickrPhotoService.savePhoto(photo, titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'article ", ErrorCodes.PHOTO_EXCEPTION);
        }

        produit.setPhoto(urlPhoto);
        return produitService.save(produit);
    }
}
