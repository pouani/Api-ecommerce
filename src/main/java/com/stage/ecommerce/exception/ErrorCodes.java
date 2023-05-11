package com.stage.ecommerce.exception;
public enum ErrorCodes {

    PRODUIT_NOT_FOUND(1000),
    PRODUIT_NOT_VALID(1001),
    CATEGORIE_NOT_FOUND(2000),
    CATEGORIE_NOT_VALID(2001),
    CLIENT_NOT_FOUND(3000),
    CLIENT_NOT_VALID(3001),
    COMMANDE_NOT_FOUND(4000),
    COMMANDE_NOT_VALID(4001),
    PANIER_NOT_FOUND(5000),
    PANIER_NOT_VALID(5001),
    LIVRAISON_NOT_FOUND(6000),
    LIVRAISON_NOT_VALID(6001),
    PAIEMENT_NOT_FOUND(7000),
    PAIEMENT_NOT_VALID(7001),
    UTILISATEUR_NOT_FOUND(8000),
    UTILISATEUR_NOT_VALID(8001),
    PANIERPRODUIT_NOT_FOUND(9000),
    PANIERPRODUIT_NOT_VALID(9001),
        ;



    private int code;
    ErrorCodes(int code) {
        this.code = code;
        }
        public int getCode() {
        return code;
    }


}