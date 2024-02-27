package com.totvs.app.bo;

import com.totvs.app.dto.request.ClienteRequest;
import com.totvs.app.dto.response.ClienteResponse;
import com.totvs.app.model.Cliente;
import com.totvs.app.model.Telefone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClienteBo {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Função responsável por fazer a conversão de dados.
     * @param request
     * @return
     */
    public Cliente criarCliente(ClienteRequest request) {
        return this.modelMapper.map(request, Cliente.class);
    }

    /**
     * Função responsável por fazer a conversão de dados.
     * @param telefones
     * @param cliente
     * @return
     */
    public List<Telefone> criarTelefones(List<String> telefones, Cliente cliente) {
        return Optional.ofNullable(telefones)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(telefone ->
                        Telefone.builder()
                                .cliente(cliente)
                                .numTelefone(telefone)
                                .build()
                ).collect(Collectors.toList());
    }

    /**
     * Função responsável por fazer a conversão de dados.
     * @param telefones
     * @return
     */
    public List<String> criarTelefonesResponse(List<Telefone> telefones) {
        return Optional.ofNullable(telefones)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Telefone::getNumTelefone)
                .collect(Collectors.toList());
    }

    /**
     * Função responsável por fazer a conversão de dados.
     * @param cliente
     * @param telefonesResponse
     * @return
     */
    public ClienteResponse criarClienteResponse(Cliente cliente, List<String> telefonesResponse) {
        ClienteResponse clienteResponse = this.modelMapper.map(cliente, ClienteResponse.class);

        clienteResponse.setTelefones(telefonesResponse);

        return clienteResponse;
    }

    /**
     * Função responsável por fazer a conversão de dados.
     * @param clientes
     * @return
     */
    public List<ClienteResponse> criarListClienteResponse(List<Cliente> clientes) {

        return Optional.ofNullable(clientes)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(cliente -> {

                    List<Telefone> telefones = cliente.getTelefones();

                    cliente.setTelefones(null);

                    ClienteResponse response = this.criarClienteResponse(
                            cliente,
                            this.criarTelefonesResponse(telefones));

                    return response;
                }).collect(Collectors.toList());
    }

}
