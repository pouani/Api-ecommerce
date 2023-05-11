package com.stage.ecommerce.services.impl;

import com.stage.ecommerce.dto.CommandeDto;
import com.stage.ecommerce.dto.CommandeProduitDto;
import com.stage.ecommerce.exception.EntityNotFoundException;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidEntityException;
import com.stage.ecommerce.model.Client;
import com.stage.ecommerce.model.Commande;
import com.stage.ecommerce.model.CommandeProduit;
import com.stage.ecommerce.model.Produit;
import com.stage.ecommerce.repository.IClientRepository;
import com.stage.ecommerce.repository.ICommandeProduitRepository;
import com.stage.ecommerce.repository.ICommandeRepository;
import com.stage.ecommerce.repository.IProduitRepository;
import com.stage.ecommerce.services.ICommandeService;
import com.stage.ecommerce.validator.CommandeValidator;
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
                   produitErrors.add("Impossible d'enregistrer une commande avec un article null");
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
    public void delete(Integer id) {
        if(id == null){
            log.error("Commande ID null");
            return;
        }
        commandeRepository.deleteById(id);
    }
}
