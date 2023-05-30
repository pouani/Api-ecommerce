package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RolesDto {
    private Integer id;

    private String name;

    @JsonIgnore
    List<UtilisateurRoleDto> utilisateurRole;

    public static RolesDto fromEntity(Roles role) {
        if (role == null) {
            return null;
        }
        return RolesDto.builder()
            .id(role.getId())
            .name(role.getName())
            .build();
    }

    public static Roles toEntity(RolesDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Roles role = new Roles();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
