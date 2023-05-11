package com.stage.ecommerce.services.impl;

import com.stage.ecommerce.dto.CategorieDto;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidEntityException;
import com.stage.ecommerce.model.Categorie;
import com.stage.ecommerce.repository.ICategorieRepository;
import com.stage.ecommerce.services.ICategorieService;
import com.stage.ecommerce.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieService implements ICategorieService {

    private ICategorieRepository categorieRepository;

    @Autowired
    public CategorieService(ICategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public CategorieDto save(CategorieDto dto) {
        List<String> errors = CategorieValidator.validator(dto);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("la categorie n'est pas valide",
                    ErrorCodes.CATEGORIE_NOT_VALID, errors);
        }
        return CategorieDto.fromEntity(categorieRepository.save(CategorieDto.toEntity(dto)));
    }

    @Override
    public CategorieDto findById(Integer id) {
        if(id == null){
            log.error("l'ID est invalid");
            return null;
        }
        Optional<Categorie> categorie = categorieRepository.findById(id);
        return Optional.of(CategorieDto.fromEntity(categorie.get())).orElseThrow(() ->
                new InvalidEntityException("Aucune categorie avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND)
        );
    }

    @Override
    public CategorieDto findByCodeCategoreie(String codeCategorie) {
        if(!StringUtils.hasLength(codeCategorie)) {
            log.error("code invalide");
            return null;
        }
        Optional<Categorie> categorie = categorieRepository.findByCodeCategorie(codeCategorie);
        return Optional.of(CategorieDto.fromEntity(categorie.get())).orElseThrow(() ->
                new InvalidEntityException("Aucune categorie avec le code = " + codeCategorie + " n'a ete trouve dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND)
        );
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll().stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            return;
        }
        categorieRepository.deleteById(id);
    }
}
