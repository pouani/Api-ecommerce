package com.stage.ecommerce.dto;

import com.stage.ecommerce.model.UtilisateurRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurRoleDto {

    private Integer id;

    private UtilisateurDto utilisateur;

    private RolesDto role;

    public static UtilisateurRoleDto fromEntity(UtilisateurRole utilisateurRole) {
        if (utilisateurRole == null) {
            return null;
        }
        return UtilisateurRoleDto.builder()
            .id(utilisateurRole.getId())
            .utilisateur(UtilisateurDto.fromEntity(utilisateurRole.getUtilisateur()))
            .role(RolesDto.fromEntity(utilisateurRole.getRole()))
            .build();
    }

    public static UtilisateurRole toEntity(UtilisateurRoleDto utilisateurRoleDto) {
        if (utilisateurRoleDto == null) {
            return null;
        }
        UtilisateurRole utilisateurRole = new UtilisateurRole();
        utilisateurRole.setId(utilisateurRoleDto.getId());
        utilisateurRole.setUtilisateur(UtilisateurDto.toEntity(utilisateurRoleDto.getUtilisateur()));
        utilisateurRole.setRole(RolesDto.toEntity(utilisateurRoleDto.getRole()));
        return utilisateurRole;
    }

}
