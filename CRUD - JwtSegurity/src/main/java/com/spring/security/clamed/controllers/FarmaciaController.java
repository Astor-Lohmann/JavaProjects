package com.spring.security.clamed.controllers;

import com.spring.security.clamed.dto.FarmaciaInput;
import com.spring.security.clamed.dto.FarmaciaOutput;
import com.spring.security.clamed.model.Farmacia;
import com.spring.security.clamed.repository.FarmaciaRepository;
import com.spring.security.clamed.service.FarmaciaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/farmacias")
public class FarmaciaController {

    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private FarmaciaService farmaciaService;


    @PostMapping
    public ResponseEntity<FarmaciaOutput> cadastrar(@RequestBody FarmaciaInput farmaciaInput){
        Farmacia farmacia = toModel(farmaciaInput);
        FarmaciaOutput farmaciaOutput = toObjectOutPut(farmaciaService.salvar(farmacia));
        return new ResponseEntity<FarmaciaOutput>(farmaciaOutput, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<FarmaciaOutput> atualizar(@RequestBody FarmaciaInput farmaciaInput){
        Farmacia farmacia = toModel(farmaciaInput);
        FarmaciaOutput farmaciaOutput = toObjectOutPut(farmaciaService.salvar(farmacia));
        return new ResponseEntity<FarmaciaOutput>(farmaciaOutput, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long idFarmacia){

        farmaciaService.delete(idFarmacia);
        return new ResponseEntity<String>("Famácia deletada com sucesso.",HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idFarmacia}")
    public ResponseEntity<String> deletePathVariable(@PathVariable(value = "idFarmacia") Long idFarmacia){

        farmaciaService.delete(idFarmacia);
        return new ResponseEntity<String>("Famácia deletada com sucesso.",HttpStatus.OK);
    }


    @GetMapping(value = "/")
    public ResponseEntity<List<FarmaciaOutput>> getUsersByName(@RequestParam (name = "nome") String nome){


        List<Farmacia> farmacias = farmaciaService.findFarmaciaByName(nome);

        List<FarmaciaOutput> farmaciasOutput = toCollectionDTOOutput(farmacias);
        return new ResponseEntity<List<FarmaciaOutput>>(farmaciasOutput, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FarmaciaOutput>> getUsuarios(){

        List<Farmacia> farmacias = farmaciaService.getFarmacias();
       // converte lista de Farmacia para lista de FarmaciaOutput
       List<FarmaciaOutput> farmaciasOutput = toCollectionDTOOutput(farmacias);

        return new ResponseEntity<List<FarmaciaOutput>>(farmaciasOutput, HttpStatus.OK);
    }


    private Farmacia toModel(FarmaciaInput farmaciaInput){
        Farmacia farmacia = new Farmacia();
        BeanUtils.copyProperties(farmaciaInput, farmacia);
        return farmacia;

    }


    private FarmaciaOutput toObjectOutPut(Farmacia farmacia){
        FarmaciaOutput farmaciaOutput = new FarmaciaOutput();
        BeanUtils.copyProperties(farmacia, farmaciaOutput);
        return farmaciaOutput;
    }


    private List<FarmaciaOutput> toCollectionDTOOutput(List<Farmacia> farmacias){
        return farmacias.stream()
                .map(farmacia -> toObjectOutPut(farmacia))
                .collect(Collectors.toList());
    }


}
