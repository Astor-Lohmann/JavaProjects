package com.spring.security.clamed.service;

import com.spring.security.clamed.exception.NotFoundException;
import com.spring.security.clamed.exception.ServerSideException;
import com.spring.security.clamed.model.Farmacia;
import com.spring.security.clamed.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public Farmacia salvar(Farmacia farmacia) {

        try {
            return farmaciaRepository.save(farmacia);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerSideException("Erro ao salvar a entrada, mensagem localizada:" + e.getLocalizedMessage());
        }

    }

    @Transactional
    public void delete(Long idFarmacia) {
        farmaciaRepository.deleteById(idFarmacia);
    }


    public Farmacia getFarmaciaById(Long idMedicamento) {
        Farmacia farmacia = farmaciaRepository.findById(idMedicamento).get();
        return farmacia;
    }


    public List<Farmacia> findFarmaciaByName(String nome) {
        return farmaciaRepository.findFarByName(nome);
    }
    

    @Transactional(readOnly = true)
    public List<Farmacia> getFarmacias(){

        List<Farmacia> farmacias = farmaciaRepository.findAll();

        return farmacias;
    }
}
