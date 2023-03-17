package com.spring.security.clamed.service;

import com.spring.security.clamed.exception.NotFoundException;
import com.spring.security.clamed.exception.ServerSideException;
import com.spring.security.clamed.model.Medicamento;
import com.spring.security.clamed.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public Medicamento salvar(Medicamento medicamento) {

        try {
            return medicamentoRepository.save(medicamento);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerSideException("Erro ao salvar a entrada, mensagem localizada:" + e.getLocalizedMessage());
        }

    }

    @Transactional
    public void delete(Long idMedicamento) {
        medicamentoRepository.deleteById(idMedicamento);
    }


    public Medicamento getUserById(Long idMedicamento) {
        Medicamento medicamento = medicamentoRepository.findById(idMedicamento).get();
        return medicamento;
    }


    public List<Medicamento> findUsersByName(String nome) {
        return medicamentoRepository.findMedByName(nome);
    }
    

    @Transactional(readOnly = true)
    public List<Medicamento> getMedicamntos(){

        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        return medicamentos;
    }
}
