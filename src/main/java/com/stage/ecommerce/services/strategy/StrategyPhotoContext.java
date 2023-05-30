package com.stage.ecommerce.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidEntityException;
import com.stage.ecommerce.services.strategy.impl.SaveClientPhoto;
import com.stage.ecommerce.services.strategy.impl.SaveProduitPhoto;
import com.stage.ecommerce.services.strategy.impl.SaveUtilisateurPhoto;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {

    private BeanFactory beanFactory;
    private Strategy strategy;

    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determineMonContexte(context);
        return strategy.savePhoto(id, photo, title);
    }

    public void determineMonContexte(String context){
        final String beanName = context + "Strategy";
        switch (context){
            case "client":
                beanFactory.getBean(beanName, SaveClientPhoto.class);
                break;
            case "utilisateur":
                beanFactory.getBean(beanName, SaveUtilisateurPhoto.class);
                break;
            case "produit":
                beanFactory.getBean(beanName, SaveProduitPhoto.class);
                break;
            default:
                throw new InvalidEntityException("Contexte inconnue pour l'enregistrement de la photo ", ErrorCodes.UNKNOWN_CONTEXT);
        }
    }
}
