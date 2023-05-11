package com.stage.ecommerce.dto;

import com.stage.ecommerce.model.Utilisateur;
import lombok.Builder;
import lombok.Data;
@Data
@Builder

public class UtilisateurDto {

    private Integer id;
    private String nomutilisateur;
    private String prenomutilisateur;
    private String email;
    private String motsdepasse;


    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if (utilisateur == null) {
            return null;
            //TODO throw an exception
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nomutilisateur(utilisateur.getNomutilisateur())
                .prenomutilisateur(utilisateur.getPrenomutilisateur())
                .email(utilisateur.getEmail())
                .motsdepasse(utilisateur.getMotsdepasse())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if (utilisateurDto == null ){
            return null;
            //TODO throw an exception
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNomutilisateur(utilisateurDto.getNomutilisateur());
        utilisateur.setPrenomutilisateur(utilisateurDto.getPrenomutilisateur());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotsdepasse(utilisateur.getMotsdepasse());

        return utilisateur;

    }
}



