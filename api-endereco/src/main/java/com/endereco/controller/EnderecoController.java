package com.endereco.controller;

import com.endereco.http.EnderecoJson;
import com.endereco.response.EnderecoResponse;
import com.endereco.service.EnderecoService;
import com.endereco.service.ViaCepService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("endereco")
public class EnderecoController {

    private final ViaCepService service;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> obterCep (@PathVariable("cep") String cep) {
        EnderecoResponse response = service.obterCep(cep);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponse> enviarEnderecoKafka(@RequestBody EnderecoJson enderecoJson) throws JsonProcessingException {
        log.info("## Dados enviado pelo o cliente: {}", enderecoJson);

        EnderecoResponse enderecoResponse = service.obterCep(enderecoJson.getCep());

        enderecoResponse.setComplemento(enderecoJson.getComplemento());
        enderecoResponse.setNumero(enderecoJson.getNumero());

        ObjectMapper objectMapper = new ObjectMapper();
        String mensagem = objectMapper.writeValueAsString(enderecoResponse);
        enderecoService.sendMessageKafka(mensagem);

        log.info("Endereço retornado pela API do CEP: {} ", enderecoResponse);

        return ResponseEntity.ok(enderecoResponse);
    }
}
