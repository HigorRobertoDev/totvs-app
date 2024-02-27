package com.totvs.app.controller;

import com.totvs.app.dto.request.ClienteRequest;
import com.totvs.app.dto.response.ClienteResponse;
import com.totvs.app.facade.ClienteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
@Api(tags = "clientes", value = "Controlador Rest API Clientes")
public class ClienteController {

    @Autowired
    private ClienteFacade clienteFacade;

    /**
     * Função responsável por receber uma requisição com o método POST e salvar os dados do cliente.
     * @param request
     * @return
     */
    @ApiOperation(value = "Salvar dados cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 500, message = "Erro na aplicação")
    })
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Valid ClienteRequest request) {

        ClienteResponse response = this.clienteFacade.criarCliente(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Função responsável por receber uma requisição com o método GET e listar todos os clientes cadastrados.
     * @return
     */
    @ApiOperation(value = "Buscar dados cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 500, message = "Erro na aplicação")
    })
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<ClienteResponse>> buscarClientes() {

        List<ClienteResponse> response = this.clienteFacade.buscarClientes();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
