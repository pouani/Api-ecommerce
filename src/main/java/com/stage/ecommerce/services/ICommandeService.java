package com.stage.ecommerce.services;

import com.stage.ecommerce.dto.CommandeDto;
import com.stage.ecommerce.dto.CommandeProduitDto;
import com.stage.ecommerce.model.EtatCommande;

import java.util.List;

public interface ICommandeService {

    CommandeDto save(CommandeDto dto);
    CommandeDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeDto updateQuantiteCommande(Integer idCommande, Integer idCommandeProduit, Integer quantite);
    CommandeDto updateClient(Integer idCommande, Integer idClient);
    CommandeDto updateProduit(Integer idCommande, Integer idCommandeProduit, Integer idProduit);

    //Delete Produit ==> delete CommandeProduit
    CommandeDto deleteProduit(Integer idCommande, Integer idCommandeProduit);
    CommandeDto findById(Integer id);
    CommandeDto findByCode(String code);
    List<CommandeDto> findAll();
    List<CommandeProduitDto> findAllCommandeProduitByCommandeId(Integer idCommande);

    void delete(Integer id);
}
