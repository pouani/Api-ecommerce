package com.stage.ecommerce.services.impl;

import com.stage.ecommerce.dto.UtilisateurDto;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidEntityException;
import com.stage.ecommerce.model.Utilisateur;
import com.stage.ecommerce.repository.IUtilisateurRepository;
import com.stage.ecommerce.services.IUtilisateurService;
import com.stage.ecommerce.validator.UtilisateurValidetor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurService implements IUtilisateurService {
    private IUtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidetor.validator(dto);
        if (!errors.isEmpty()){
            log.error("Utilisateur invqlid {}", dto);
            throw new InvalidEntityException("utilisateur invalide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if(id == null){
            log.error("ID utilisateur invalid");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(() ->
                new InvalidEntityException("Aucun utilisateur avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) return;
        utilisateurRepository.deleteById(id);
    }
}
