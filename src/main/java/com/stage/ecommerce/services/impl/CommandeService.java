package com.stage.ecommerce.services.impl;

import com.stage.ecommerce.dto.ClientDto;
import com.stage.ecommerce.dto.CommandeDto;
import com.stage.ecommerce.dto.CommandeProduitDto;
import com.stage.ecommerce.dto.ProduitDto;
import com.stage.ecommerce.exception.EntityNotFoundException;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidEntityException;
import com.stage.ecommerce.exception.InvalidOperationException;
import com.stage.ecommerce.model.*;
import com.stage.ecommerce.repository.IClientRepository;
import com.stage.ecommerce.repository.ICommandeProduitRepository;
import com.stage.ecommerce.repository.ICommandeRepository;
import com.stage.ecommerce.repository.IProduitRepository;
import com.stage.ecommerce.services.ICommandeService;
import com.stage.ecommerce.validator.CommandeValidator;
import com.stage.ecommerce.validator.ProduitValidetor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeService implements ICommandeService {

    private ICommandeRepository commandeRepository;

    private IProduitRepository produitRepository;

    private ICommandeProduitRepository commandeProduitRepository;

    private IClientRepository clientRepository;

    @Autowired
    public CommandeService(
            ICommandeRepository commandeRepository,
            IProduitRepository produitRepository,
            ICommandeProduitRepository commandeProduitRepository,
            IClientRepository clientRepository)
    {
        this.commandeRepository = commandeRepository;
        this.produitRepository = produitRepository;
        this.commandeProduitRepository = commandeProduitRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CommandeDto save(CommandeDto dto) {
        List<String> errors = CommandeValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Commande invalide");
            throw new InvalidEntityException("La commande n'est pas valide", ErrorCodes.COMMANDE_NOT_VALID, errors);
        }

        if(dto.getId() != null && dto.isCommandeLivre()){
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }

        //verification si le client existe
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if(client.isEmpty()){
            log.warn("Client with ID {} not found in DB", dto.getClient().getId());
            throw new EntityNotFoundException("ID client non trouve", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> produitErrors = new ArrayList<>();

        if(dto.getCommandeProduit() != null){
           dto.getCommandeProduit().forEach(cmdP -> {
               if(cmdP.getProduit() != null){
                   Optional<Produit> produit = produitRepository.findById(cmdP.getProduit().getId());
                   if(produit.isEmpty()){
                        produitErrors.add("Le produit avec l'ID = " + cmdP.getProduit().getId() + "n'existe pas");
                   }
               }else{
                   produitErrors.add("Impossible d'enregistrer une commande avec un produit null");
               }

           });
        }

        if(!produitErrors.isEmpty()){
            log.warn("Produit inconnu");
            throw new InvalidEntityException("le produit n'existe pas dans la BDD", ErrorCodes.PRODUIT_NOT_VALID, produitErrors);
        }
        Commande saveCommande = commandeRepository.save(CommandeDto.toEntity(dto));

        if(dto.getCommandeProduit() != null){
            dto.getCommandeProduit().forEach(cmdP -> {
                CommandeProduit commandeProduit = CommandeProduitDto.toEntity(cmdP);
                commandeProduit.setCommande(saveCommande);
                commandeProduitRepository.save(commandeProduit);
            });
        }

        return CommandeDto.fromEntity(saveCommande);
    }

    @Override
    public CommandeDto findById(Integer id) {
        if(id == null){
            log.error("Commande ID null");
            return null;
        }
        return commandeRepository.findById(id)
                .map(CommandeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande n'a ete trouve avec l'ID = " + id + " dans la BDD",
                        ErrorCodes.COMMANDE_NOT_FOUND
                ));
    }

    @Override
    public CommandeDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande code null");
            return null;
        }
        return commandeRepository.findByCodeCommande(code)
                .map(CommandeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande n'a ete trouve avec le CODE = " + code + " dans la BDD",
                        ErrorCodes.COMMANDE_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeDto> findAll() {
        return commandeRepository.findAll().stream()
                .map(CommandeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeProduitDto> findAllCommandeProduitByCommandeId(Integer idCommande) {
        return commandeProduitRepository.findAllByCommandeId(idCommande).stream()
                .map(CommandeProduitDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Commande ID null");
            return;
        }
        commandeRepository.deleteById(id);
    }


    @Override
    public CommandeDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande){
        checkIdCommande(idCommande);

        if(!StringUtils.hasLength(String.valueOf(etatCommande))){
            log.error("State Order Client is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande qui n'existe ",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }

        CommandeDto commande = findById(idCommande);

        if(commande.isCommandeLivre()){
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }

        commande.setStatutcommande(etatCommande);

        return CommandeDto.fromEntity(
                commandeRepository.save(CommandeDto.toEntity(commande))
        );
    }

    @Override
    public CommandeDto updateQuantiteCommande(Integer idCommande, Integer idCommandeProduit, Integer quantite) {

        checkIdCommande(idCommande);
        checkIdCommandeProduit(idCommandeProduit);
        if(quantite == null || quantite.compareTo(Integer.MAX_VALUE) == 0){
            log.error("Quantity Order Product is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite = ZERO (0) ",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }

        CommandeDto commande = checkEtatCommande(idCommande);

        Optional<CommandeProduit> commandeProduitOptional = findCommandeProduit(idCommandeProduit);

        CommandeProduit commandeProduit = commandeProduitOptional.get();

        commandeProduit.setQuantite(quantite);

        commandeProduitRepository.save(commandeProduit);

        return commande;
    }


    @Override
    public CommandeDto updateClient(Integer idCommande, Integer idClient) {

        checkIdCommande(idCommande);
        if(idClient == null){
            log.error("Client ID is null");
            throw new InvalidOperationException("Impossible de modifier le Client avec un ID null de la commande ",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }
        CommandeDto commande = checkEtatCommande(idCommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);

        if(clientOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucun client n'a ete trouve avec l'ID = " + idClient + " dans la BDD",
                    ErrorCodes.CLIENT_NOT_FOUND);
        }

        commande.setClient(ClientDto.fromEntity(clientOptional.get()));

        return CommandeDto.fromEntity(
                commandeRepository.save(CommandeDto.toEntity(commande))
        );
    }

    @Override
    public CommandeDto updateProduit(Integer idCommande, Integer idCommandeProduit, Integer idProduit) {
        checkIdCommande(idCommande);
        checkIdCommandeProduit(idCommandeProduit);
        checkIdProduit(idProduit, "nouvel");
        CommandeDto commande = checkEtatCommande(idCommande);

        Optional<CommandeProduit> commandeProduit = findCommandeProduit(idCommandeProduit);

        Optional<Produit> produitOptional = produitRepository.findById(idProduit);

        if(produitOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucun Produit n'a ete trouve avec l'ID = " + idProduit + " dans la BDD",
                    ErrorCodes.PRODUIT_NOT_FOUND);
        }

        List<String> errors = ProduitValidetor.validator(ProduitDto.fromEntity(produitOptional.get()));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Produit invalid", ErrorCodes.PRODUIT_NOT_VALID, errors);
        }

        CommandeProduit commandeProduitToSava = commandeProduit.get();
        commandeProduitToSava.setProduit(produitOptional.get());

        commandeProduitRepository.save(commandeProduitToSava);
        return commande;
    }

    @Override
    public CommandeDto deleteProduit(Integer idCommande, Integer idCommandeProduit) {
        checkIdCommande(idCommande);
        checkIdCommandeProduit(idCommandeProduit);

        CommandeDto commande = checkEtatCommande(idCommande);
        findCommandeProduit(idCommandeProduit);
        commandeProduitRepository.deleteById(idCommandeProduit);

        return commande;
    }

    private Optional<CommandeProduit> findCommandeProduit(Integer idCommandeProduit) {
        Optional<CommandeProduit> commandeProduitOptional = commandeProduitRepository.findById(idCommandeProduit);

        if(commandeProduitOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucune commande produit n'a ete trouve avec l'ID = " + idCommandeProduit + " dans la BDD",
                    ErrorCodes.COMMANDE_NOT_FOUND);
        }
        return commandeProduitOptional;
    }

    private void checkIdCommande(Integer idCommande){
        if(idCommande == null){
            log.error("Commande ID null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null ",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }
    }

    private CommandeDto checkEtatCommande(Integer idCommande) {
        CommandeDto commande = findById(idCommande);

        if(commande.isCommandeLivre()){
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }

        return commande;
    }
    private void checkIdCommandeProduit(Integer idCommandeProduit){
        if(idCommandeProduit == null){
            log.error("Order Product ID is null");
            throw new InvalidOperationException("Impossible de modifier la quantitee de la commande qui n'existe ",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }
    }

    private void checkIdProduit(Integer idProduit, String msg){
        if(idProduit == null){
            log.error("ID "+ msg +" Product is null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un "+ msg +" ID produit null.",
                    ErrorCodes.COMMANDE_NON_MODIFIABLE);
        }
    }

}
