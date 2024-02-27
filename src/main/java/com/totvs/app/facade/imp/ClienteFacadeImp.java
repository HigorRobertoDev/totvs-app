package com.totvs.app.facade.imp;

import com.totvs.app.bo.ClienteBo;
import com.totvs.app.dto.request.ClienteRequest;
import com.totvs.app.dto.response.ClienteResponse;
import com.totvs.app.facade.ClienteFacade;
import com.totvs.app.model.Cliente;
import com.totvs.app.model.Telefone;
import com.totvs.app.service.ClienteService;
import com.totvs.app.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteFacadeImp implements ClienteFacade {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private ClienteBo clienteBo;

    /**
     * Função responsável por converter os dados, fazer validação dos telefones, chamar o service para salvar os
     * dados do cliente e preparar a resposta.
     * @param request
     * @return
     */
    @Override
    public ClienteResponse criarCliente(ClienteRequest request) {

        Cliente clienteParaSalvar =
                this.clienteBo.criarCliente(request);

        List<Telefone> telefonesParaSalvar =
                this.clienteBo.criarTelefones(request.getTelefones(), clienteParaSalvar);

        clienteParaSalvar.setTelefones(telefonesParaSalvar);

        this.telefoneService.verificarTelefonesJaCadastrados(clienteParaSalvar.getTelefones());

        Cliente clienteSalvo =
                clienteService.salvarCliente(clienteParaSalvar);

        List<String> telefonesResponse =
                this.clienteBo.criarTelefonesResponse(clienteSalvo.getTelefones());

        clienteSalvo.setTelefones(null);

        ClienteResponse response =
                this.clienteBo.criarClienteResponse(clienteSalvo, telefonesResponse);

        return response;
    }

    /**
     * Função responsavel por chamar o service e buscar os clientes cadastrados.
     * @return
     */
    @Override
    public List<ClienteResponse> buscarClientes() {

        List<Cliente> clientes = this.clienteService.buscarClientes();

        return this.clienteBo.criarListClienteResponse(clientes);
    }
}
