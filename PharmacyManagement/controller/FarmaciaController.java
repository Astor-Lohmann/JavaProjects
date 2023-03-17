package com.example.PharmacyManagement.controller;


import com.example.PharmacyManagement.controller.dto.EnderecoResponse;
import com.example.PharmacyManagement.controller.dto.FarmaciaResponse;
import com.example.PharmacyManagement.data.entity.FarmaciaEntity;
import com.example.PharmacyManagement.data.repository.EnderecoRepository;
import com.example.PharmacyManagement.data.repository.FarmaciaRepository;
import com.example.PharmacyManagement.padroes.DefaultResponse;
import com.example.PharmacyManagement.service.FarmaciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("farmacia")
public class FarmaciaController {


    private final FarmaciaRepository farmaciaRepository;

    private final EnderecoRepository enderecoRepository;

    private final FarmaciaService farmaciaService;

    public FarmaciaController(FarmaciaRepository farmaciaRepository, EnderecoRepository enderecoRepository, FarmaciaService farmaciaService) {
        this.farmaciaRepository = farmaciaRepository;
        this.enderecoRepository = enderecoRepository;
        this.farmaciaService = farmaciaService;
    }

    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> encontraFarmacias() {
        List<FarmaciaResponse> responseList = farmaciaService.listaTodasFarmacias();

        return ResponseEntity.ok(responseList);


        @PostMapping("/farmacia")
        public ResponseEntity<DefaultResponse> salvarNota (@Valid @RequestBody FarmaciaRequest farmaciaRequest)
        { // corpo de requisição
            FarmaciaResponse response = farmaciaService.salvarNovaFarmacia(farmaciaRequest);

            return new ResponseEntity<>(
                    new DefaultResponse<FarmaciaResponse>(
                            HttpStatus.CREATED.value(), // valor int do status Created
                            response
                    ),
                    HttpStatus.CREATED
            );

        }


        @GetMapping("/farmacia")

        public ResponseEntity<FarmaciaResponse> encontrarNotaPorId (@RequestParam(value = "id", required = false) Long
        id){
            FarmaciaEntity
                    entity = farmaciaRepository
                    .findById(id).get();

            return new ResponseEntity<FarmaciaResponse>(
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

                            ),
                            HttpStatus.OK
                    );
        }


        @PutMapping("/farmacia/{id}")
        public ResponseEntity<FarmaciaResponse> atualizarNotaPorId (
                @PathVariable Long id,
                @RequestBody FarmaciaRequest request)
        {
            FarmaciaEntity
                    entity = farmaciaRepository
                    .findById(id).get();
            entity.setRazaoSocial(request.getRazaoSocial());
            entity.setCnpj(request.getCnpj());
            entity.setNome(request.getNome());
            entity.setEmail(request.getEmail());
            entity.setTelefone(request.getTelefone());
            entity.setCelular(request.getCelular());

            farmaciaRepository.save(entity);

            return new ResponseEntity<FarmaciaResponse>(

                    FarmaciaResponse.builder()
                            .setRazaoSocial(request.getRazaoSocial());
                        .setCnpj(request.getCnpj());
                        .setNome(request.getNome());
                        .setEmail(request.getEmail());
                        .setTelefone(request.getTelefone());
                        .setCelular(request.getCelular());
                        .build(),

                    HttpStatus.OK // status ok
        );
        }

        @DeleteMapping("/{id}")
        public ResponseEntity deletarFarmaciaPorId (
                @PathVariable Long id
    ){
            farmaciaRepository
                    .deleteById(id); // deleta a nota de id {id}
            return ResponseEntity.ok().build();
        }
    }
}
