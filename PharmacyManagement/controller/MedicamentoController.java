package com.example.PharmacyManagement.controller;

import com.example.PharmacyManagement.controller.dto.MedicamentoResponse
        ;
import com.example.PharmacyManagement.data.entity.MedicamentoEntity;
import com.example.PharmacyManagement.data.repository.MedicamentoRepository
        ;
import com.example.PharmacyManagement.padroes.DefaultResponse;
import com.example.PharmacyManagement.service.MedicamentoService
        ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MedicamentoController {


    private final MedicamentoRepository medicamentoRepository;

    private final MedicamentoService medicamentoService;


    public FarmaciaController(MedicamentoRepository medicamentoRepository, MedicamentoService medicamentoService) {
        this.medicamentoRepository = medicamentoRepository;
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse
            >> encontraMedicamento() {
        List<MedicamentoResponse> responseList = medicamentoService.listaTodasFarmacias();

        return ResponseEntity.ok(responseList);


        @PostMapping("/medicamento")
        public ResponseEntity<DefaultResponse> salvarMedicamento (@Valid @RequestBody MedicamentoRequest
        medicamentoRequest)
        { // corpo de requisição
            MedicamentoResponse response = medicamentoService.salvarNovoMedicamento(medicamentoRequest);

            return new ResponseEntity<>(
                    new DefaultResponse<MedicamentoResponse>(
                            HttpStatus.CREATED.value(), // valor int do status Created
                            response
                    ),
                    HttpStatus.CREATED
            );

        }


        @GetMapping("/medicamento")

        public ResponseEntity<MedicamentoResponse> encontrarMedicamentoPorId
        (@RequestParam(value = "id", required = false) Long id){
            MedicamentoEntity
                    entity = medicamentoRepository

                    .findById(id).get();

            return new ResponseEntity<MedicamentoResponse>(
                    new MedicamentoResponse(
                            entity.getId(),
                            entity.getNome(),
                            entity.getLaboratorio(),
                            entity.getDosagem(),
                            entity.getDescricao(),
                            entity.getDescricaoop(),
                            entity.getPreco(),
                            entity.getTipo()

                            HttpStatus.OK
                    );
        }


        @PutMapping("/medicamento/{id}")
        public ResponseEntity<MedicamentoResponse> atualizarMedicamentoPorId (
                @PathVariable Long id,
                @RequestBody MedicamentoRequest request ){
            MedicamentoEntity entity = medicamentoRepository.findById(id).get();
            entity.setNome(request.getNome());
            entity.setLaboratorio(request.getLaboratorio());
            entity.setDosagem(request.getDosagem());
            entity.setDescricao(request.getDescricao());
            entity.setDescricaoop(request.getDescricaoop());
            entity.setPreco(request.getPreco());
            entity.setTipo(request.getTipo());

            medicamentoRepository.save(entity);

            return new ResponseEntity<MedicamentoResponse>(

                    MedicamentoResponse.builder()
                    .nome(request.getNome())
                    .laboratorio(request.getLaboratorio())
                    .dosagem(request.getDosagem())
                    .descricao(request.getDescricao())
                    .descricaoop(request.getDescricaoop())
                    .preco(request.getPreco())
                    .tipo(request.getTipo())

            HttpStatus.OK // status ok
        );
        }

        @DeleteMapping("/{id}")
        public ResponseEntity deletarMedicamentoPorId (
                @PathVariable Long id
    ){
            medicamentoRepository

                    .deleteById(id); // deleta a nota de id {id}
            return ResponseEntity.ok().build();
        }
    }
}
