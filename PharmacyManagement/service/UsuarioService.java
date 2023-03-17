package com.example.PharmacyManagement.service;

import com.example.PharmacyManagement.controller.dto.UsuarioRequest
        ;
import com.example.PharmacyManagement.controller.dto.UsuarioResponse
        ;
import com.example.PharmacyManagement.data.entity.UsuarioEntity
        ;
import com.example.PharmacyManagement.data.repository.UsuarioRepository
        ;
import com.example.PharmacyManagement.exception.NotFoundException;
import com.example.PharmacyManagement.exception.ServerSideException;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository
            usuarioRepository;


    public UsuarioService(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    public List<UsuarioResponse
            > listaTodasFarmacias() {
        List<UsuarioEntity> entityList = usuarioRepository.findAll();

        List<UsuarioResponse> responseList = new ArrayList<>();

        for (UsuarioEntity

                entity : entityList) {
            responseList.add(
                    new UsuarioResponse

                            (
                                    entity.getId(),
                                    entity.getEmail(),
                                    entity.getSenha()

                            )
            );
        }

        return responseList;
    }

    public UsuarioResponse

    salvarNovoUsuario(UsuarioRequest

                              usuarioRequest

    ) {
        try {

            UsuarioEntity entity = usuarioRepository.save(new UsuarioEntity(usuarioRequest.getRazaoSocial()
                    , usuarioRequest.getId()
                    , usuarioRequest.getEmail()
                    , usuarioRequest.getSenha()

            ));
            return new UsuarioResponse
                    (
                            entity.getId(),
                            entity.getEmail(),
                            entity.getSenha()
                    );
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerSideException("Erro ao salvar a entrada, mensagem localizada:" + e.getLocalizedMessage());

        }
    }
}
