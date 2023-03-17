package com.spring.security.clamed.controllers;

import com.spring.security.clamed.dto.MedicamentoInput;
import com.spring.security.clamed.dto.MedicamentoOutput;
import com.spring.security.clamed.model.Medicamento;
import com.spring.security.clamed.repository.MedicamentoRepository;
import com.spring.security.clamed.service.MedicamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/medicamentos")
public class MedicamentoController {

    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private MedicamentoService medicamentoService;


    @PostMapping
    public ResponseEntity<MedicamentoOutput> cadastrar(@RequestBody MedicamentoInput medicamentoInput){
        Medicamento medicamento = toModel(medicamentoInput);
        MedicamentoOutput medicamentoOutput = toObjectOutPut(medicamentoService.salvar(medicamento));
        return new ResponseEntity<MedicamentoOutput>(medicamentoOutput, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<MedicamentoOutput> atualizar(@RequestBody MedicamentoInput medicamentoInput){
        Medicamento medicamento = toModel(medicamentoInput);
        MedicamentoOutput medicamentoOutput = toObjectOutPut(medicamentoService.salvar(medicamento));
        return new ResponseEntity<MedicamentoOutput>(medicamentoOutput, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long idMedicamento){

        medicamentoService.delete(idMedicamento);
        return new ResponseEntity<String>("Medicamento deletado com sucesso.",HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idMedicamento}")
    public ResponseEntity<String> deletePathVariable(@PathVariable(value = "idMedicamento") Long idMedicamento){

        medicamentoService.delete(idMedicamento);
        return new ResponseEntity<String>("Medicamento deletado com sucesso.",HttpStatus.OK);
    }


    @GetMapping(value = "/")
    public ResponseEntity<List<MedicamentoOutput>> getUsersByName(@RequestParam (name = "nome") String nome){

 
        List<Medicamento> medicamentos = medicamentoService.findUsersByName(nome);

        List<MedicamentoOutput> medicamentosOutput = toCollectionDTOOutput(medicamentos);
        return new ResponseEntity<List<MedicamentoOutput>>(medicamentosOutput, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoOutput>> getMedicamntos(){
       // obtem a lista de medicamentos cadastrados
        List<Medicamento> medicamentos = medicamentoService.getMedicamntos();
       // converte lista de Medicamento para lista de MedicamentoOutput
       List<MedicamentoOutput> medicamentosOutput = toCollectionDTOOutput(medicamentos);

        return new ResponseEntity<List<MedicamentoOutput>>(medicamentosOutput, HttpStatus.OK);
    }


    private Medicamento toModel(MedicamentoInput medicamentoInput){
        Medicamento medicamento = new Medicamento();
        BeanUtils.copyProperties(medicamentoInput, medicamento);
        return medicamento;

    }


    private MedicamentoOutput toObjectOutPut(Medicamento medicamento){
        MedicamentoOutput medicamentoOutput = new MedicamentoOutput();
        BeanUtils.copyProperties(medicamento, medicamentoOutput);
        return medicamentoOutput;
    }


    private List<MedicamentoOutput> toCollectionDTOOutput(List<Medicamento> medicamentos){
        return medicamentos.stream()
                .map(medicamento -> toObjectOutPut(medicamento))
                .collect(Collectors.toList());
    }


}
