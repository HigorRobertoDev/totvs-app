package com.totvs.app.service;

import com.totvs.app.model.Cliente;
import com.totvs.app.model.Telefone;
import com.totvs.app.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void test_salvarCliente() {

        Cliente request = this.criarClienteRequest();
        Cliente response = this.criarClienteResponse();

        when(this.clienteRepository.saveAndFlush(request)).thenReturn(response);

        Cliente expected = this.clienteService.salvarCliente(request);

        assertEquals(expected, response);

        verify(this.clienteRepository, times(1)).saveAndFlush(request);
    }

    @Test
    public void test_buscarClientes() {

        List<Cliente> response = Arrays.asList(this.criarClienteResponse());

        when(this.clienteRepository.findAll()).thenReturn(response);

        List<Cliente> expected = this.clienteService.buscarClientes();

        assertEquals(expected, response);

        verify(this.clienteRepository, times(1)).findAll();
    }

    private Cliente criarClienteRequest() {

        List<Telefone> telefones = Arrays.asList(
                Telefone.builder()
                        .numTelefone("(99) 99999-9999")
                        .build()
        );

        return Cliente.builder()
                .nome("Higão")
                .bairro("Morro do Mirante")
                .endereco("Rua das Capivaras")
                .telefones(telefones)
                .build();
    }

    private Cliente criarClienteResponse() {

        List<Telefone> telefones = Arrays.asList(
                Telefone.builder()
                        .numTelefone("(99) 99999-9999")
                        .build()
        );

        return Cliente.builder()
                .id(1)
                .nome("Higão")
                .bairro("Morro do Mirante")
                .endereco("Rua das Capivaras")
                .telefones(telefones)
                .build();
    }

}
