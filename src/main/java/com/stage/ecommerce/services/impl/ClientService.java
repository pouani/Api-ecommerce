package com.stage.ecommerce.services.impl;

import com.stage.ecommerce.dto.ClientDto;
import com.stage.ecommerce.exception.EntityNotFoundException;
import com.stage.ecommerce.exception.ErrorCodes;
import com.stage.ecommerce.exception.InvalidEntityException;
import com.stage.ecommerce.model.Client;
import com.stage.ecommerce.repository.IClientRepository;
import com.stage.ecommerce.services.IClientService;
import com.stage.ecommerce.validator.ClientValidetor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService implements IClientService {

    private IClientRepository clientRepository;
    @Autowired
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidetor.validator(dto);
        if(!errors.isEmpty()){
            log.error("Client invalid {}", dto);
            throw new InvalidEntityException("le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if(id == null) {
            log.error("l'ID du client est null");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);
        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun client avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND)
        );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("l'ID du client est null");
            return;
        }
        clientRepository.deleteById(id);
    }
}
