package com.endereco.controller;

import com.endereco.response.EnderecoResponse;
import com.endereco.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cep")
public class CepController {

    private final ViaCepService service;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> obterCep (@PathVariable("cep") String cep) {
        EnderecoResponse response = service.obterCep(cep);

        return ResponseEntity.ok(response);
    }
}
