package com.stage.ecommerce.services.impl;

import com.stage.ecommerce.dto.ProduitDto;
import com.stage.ecommerce.exception.EntityNotFoundException;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidEntityException;
import com.stage.ecommerce.model.Produit;
import com.stage.ecommerce.repository.IProduitRepository;
import com.stage.ecommerce.services.IProduitService;
import com.stage.ecommerce.validator.ProduitValidetor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProduitService implements IProduitService {

    private IProduitRepository produitRepository;

    @Autowired
    public ProduitService(IProduitRepository produitRepository){
        this.produitRepository = produitRepository;
    }

    @Override
    public ProduitDto save(ProduitDto dto) {
        List<String> errors = ProduitValidetor.validator(dto);
        if(!errors.isEmpty()){
            log.error("Produit invalid {}", dto);
            throw new InvalidEntityException("le produit n'est pas valide", ErrorCodes.PRODUIT_NOT_VALID, errors);
        }
        return ProduitDto.fromEntity(produitRepository.save(ProduitDto.toEntity(dto)));
    }

    @Override
    public ProduitDto findById(Integer id) {
        if(id == null) {
            log.error("l'ID du profuit est null");
            return null;
        }
        Optional<Produit> produit = produitRepository.findById(id);

        return Optional.of(ProduitDto.fromEntity(produit.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun produit avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.PRODUIT_NOT_FOUND)
        );
    }

    @Override
    public ProduitDto findByCodeProduit(String codeProduit) {
        if (!StringUtils.hasLength(codeProduit)) {
            return null;
        }
        Optional<Produit> produit = produitRepository.findProduitByCodeProduit(codeProduit);

        return Optional.of(ProduitDto.fromEntity(produit.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun produit avec le CODE = " + codeProduit + " n'a ete trouve dans la BDD",
                        ErrorCodes.PRODUIT_NOT_FOUND)
        );

    }

    @Override
    public List<ProduitDto> findAll() {
        return produitRepository.findAll().stream()
                .map(ProduitDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("l'ID du produit est null");
            return;
        }
        produitRepository.deleteById(id);
    }
}
