package com.example.PharmacyManagement.service;


import com.example.PharmacyManagement.controller.dto.EnderecoResponse;
import com.example.PharmacyManagement.controller.dto.FarmaciaRequest;
import com.example.PharmacyManagement.controller.dto.FarmaciaResponse;
import com.example.PharmacyManagement.data.entity.EnderecoEntity;
import com.example.PharmacyManagement.data.entity.FarmaciaEntity;
import com.example.PharmacyManagement.data.repository.EnderecoRepository;
import com.example.PharmacyManagement.data.repository.FarmaciaRepository;
import com.example.PharmacyManagement.exception.NotFoundException;
import com.example.PharmacyManagement.exception.ServerSideException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmaciaService {
    @Autowired
    private final FarmaciaRepository farmaciaRepository;

    private final EnderecoRepository enderecoRepository;

    public FarmaciaService(FarmaciaRepository repository, EnderecoRepository enderecoRepository) {
        this.farmaciaRepository = repository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<FarmaciaResponse> listaTodasFarmacias() {
        List<FarmaciaEntity> entityList = farmaciaRepository.findAll();

        List<FarmaciaResponse> responseList = new ArrayList<>();

        for (FarmaciaEntity entity : entityList) {
            responseList.add(
                    new FarmaciaResponse(
                            entity.getId(),
                            entity.getRazaoSocial(),
                            entity.getCnpj(),
                            entity.getNome(),
                            entity.getEmail(),
                            entity.getTelefone(),
                            entity.getCelular(),
                            new EnderecoResponse(
                                    entity.getEnderecoEntity().getCep(),
                                    entity.getEnderecoEntity().getLogradouro(),
                                    entity.getEnderecoEntity().getNumero(),
                                    entity.getEnderecoEntity().getBairro(),
                                    entity.getEnderecoEntity().getCidade(),
                                    entity.getEnderecoEntity().getEstado(),
                                    entity.getEnderecoEntity().getComplemento(),
                                    entity.getEnderecoEntity().getLatitude(),
                                    entity.getEnderecoEntity().getLongitude()

                            )
                    )
            );
        }

        return responseList;
    }

    public FarmaciaResponse salvarNovaFarmacia(FarmaciaRequest farmaciaRequest) {
        try {
            EnderecoEntity enderecoEntity = enderecoRepository.findById(farmaciaRequest.getIdEndereço())
                    .orElseThrow(() -> new NotFoundException("Endereço nao encontrado"));


            FarmaciaEntity entity = farmaciaRepository.save(new FarmaciaEntity(farmaciaRequest.getRazaoSocial()
                    , farmaciaRequest.getCnpj()
                    , farmaciaRequest.getNome()
                    , farmaciaRequest.getEmail()
                    , farmaciaRequest.getTelefone()
                    , farmaciaRequest.getCelular()
                    , enderecoEntity
            ));
            return new FarmaciaResponse(
                    entity.getId(),
                    entity.getRazaoSocial(),
                    entity.getCnpj(),
                    entity.getNome(),
                    entity.getEmail(),
                    entity.getTelefone(),
                    entity.getCelular(),
                    new EnderecoResponse(
                            entity.getEnderecoEntity().getCep(),
                            entity.getEnderecoEntity().getLogradouro(),
                            entity.getEnderecoEntity().getNumero(),
                            entity.getEnderecoEntity().getBairro(),
                            entity.getEnderecoEntity().getCidade(),
                            entity.getEnderecoEntity().getEstado(),
                            entity.getEnderecoEntity().getComplemento(),
                            entity.getEnderecoEntity().getLatitude(),
                            entity.getEnderecoEntity().getLongitude()

                    )
            );
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerSideException("Erro ao salvar a entrada, mensagem localizada:" + e.getLocalizedMessage());

        }
    }
}
