package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder

public class ClientDto {

    private Integer id;
    private String nomclient;
    private String prenomclient;
    private String telephoneclient;
    private String adresseClient;
    private String photo;

    @JsonIgnore
    private List<CommandeDto> commande;


    public static ClientDto fromEntity(Client client){
        if (client==null) {
            return null;
            //TODO throw an exception
        }

        return ClientDto.builder()
                .id(client.getId())
                .nomclient(client.getNomclient())
                .prenomclient(client.getPrenomclient())
                .telephoneclient(client.getTelephoneclient())
                .adresseClient(client.getAdresseClient())
                .photo(client.getPhoto())
                .build();
    }

    public static Client toEntity(ClientDto clientDto){
        if (clientDto == null ){
            return null;
            //TODO throw an exception
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNomclient(clientDto.getNomclient());
        client.setPrenomclient(clientDto.getPrenomclient());
        client.setAdresseClient(clientDto.getAdresseClient());
        client.setPhoto(clientDto.getPhoto());

        return client;
    }
}




