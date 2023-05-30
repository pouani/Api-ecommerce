package com.stage.ecommerce.dto;

import com.stage.ecommerce.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;
    private String nomutilisateur;
    private String prenomutilisateur;
    private String email;
    private String motsdepasse;
    private String photo;


    private List<UtilisateurRoleDto> utilisateurRole;


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
                .photo(utilisateur.getPhoto())
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
        utilisateur.setMotsdepasse(utilisateurDto.getMotsdepasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());

        return utilisateur;

    }
}



