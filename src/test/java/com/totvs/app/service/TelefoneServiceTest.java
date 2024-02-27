package com.totvs.app.service;

import com.totvs.app.common.exceptions.BusinessException;
import com.totvs.app.model.Telefone;
import com.totvs.app.repository.TelefoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
public class TelefoneServiceTest {

    @InjectMocks
    private TelefoneService telefoneService;

    @Mock
    private TelefoneRepository telefoneRepository;

    @Test
    public void test_caso1_verificarTelefonesJaCadastrados() {

        List<Telefone> telefones = criarTelefone();

        List<String> numTelefones = Arrays.asList("(99) 99999-9999");

        when(this.telefoneRepository.existsByNumTelefoneIn(numTelefones)).thenReturn(false);

        this.telefoneService.verificarTelefonesJaCadastrados(telefones);

        verify(this.telefoneRepository, times(1)).existsByNumTelefoneIn(numTelefones);
    }

    @Test
    public void test_caso2_verificarTelefonesJaCadastrados() {

        List<Telefone> telefones = criarTelefone();

        List<String> numTelefones = Arrays.asList("(99) 99999-9999");

        when(this.telefoneRepository.existsByNumTelefoneIn(numTelefones)).thenReturn(true);

        Assertions.assertThrows(BusinessException.class, () ->
                this.telefoneService.verificarTelefonesJaCadastrados(telefones));
    }

    public List<Telefone> criarTelefone() {

        return Arrays.asList(
                Telefone.builder()
                        .numTelefone("(99) 99999-9999")
                        .build()
        );
    }
}
