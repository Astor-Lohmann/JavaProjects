package com.example.PharmacyManagement.service;

import com.example.PharmacyManagement.controller.dto.MedicamentoRequest
        ;
import com.example.PharmacyManagement.controller.dto.MedicamentoResponse
        ;
import com.example.PharmacyManagement.data.entity.MedicamentoEntity
        ;
import com.example.PharmacyManagement.data.repository.MedicamentoRepository
        ;
import com.example.PharmacyManagement.exception.NotFoundException;
import com.example.PharmacyManagement.exception.ServerSideException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MedicamentoService {

    @Autowired
    private final MedicamentoRepository medicamentoRepository;


    public MedicamentoService(MedicamentoRepository repository) {
        this.medicamentoRepository = repository;
    }

    public List<MedicamentoResponse
            > listaTodasFarmacias() {
        List<MedicamentoEntity
                > entityList = medicamentoRepository
                .findAll();

        List<MedicamentoResponse
                > responseList = new ArrayList<>();

        for (MedicamentoEntity
                entity : entityList) {
            responseList.add(
                    new MedicamentoResponse
                            (
                                    entity.getId(),
                                    entity.getNome(),
                                    entity.getLaboratorio(),
                                    entity.getDosagem(),
                                    entity.getDescricao(),
                                    entity.getDescricaoop(),
                                    entity.getPreco(),
                                    entity.getTipo()

                            )
            );
        }

        return responseList;
    }

    public MedicamentoResponse
    salvarNovoMedicamento(MedicamentoRequest
                                  medicamentoRequest
    ) {
        try {

            MedicamentoEntity entity = medicamentoRepository.save(new MedicamentoEntity(medicamentoRequest.getRazaoSocial()
                    , medicamentoRequest.getNome()
                    , medicamentoRequest.getLaboratorio()
                    , medicamentoRequest.getDosagem()
                    , medicamentoRequest.getDescricao()
                    , medicamentoRequest.getDescricaoop()
                    , medicamentoRequest.getPreco()
                    , medicamentoRequest.getTipo()


            ));
            return new MedicamentoResponse(
                    entity.getId(),
                    entity.getNome(),
                    entity.getLaboratorio(),
                    entity.getDosagem(),
                    entity.getDescricao(),
                    entity.getDescricaoop(),
                    entity.getPreco(),
                    entity.getTipo()
            );
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerSideException("Erro ao salvar a entrada, mensagem localizada:" + e.getLocalizedMessage());

        }
    }
}
