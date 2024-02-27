package com.totvs.app.service;

import com.totvs.app.common.exceptions.BusinessException;
import com.totvs.app.common.exceptions.enums.BusinessErroEnum;
import com.totvs.app.model.Telefone;
import com.totvs.app.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    /**
     * Função responsável por verificar números já cadastrados na base.
     * @param telefones
     */
    public void verificarTelefonesJaCadastrados(List<Telefone> telefones) {

        List<String> numTelefones = Optional.ofNullable(telefones)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Telefone::getNumTelefone)
                .collect(Collectors.toList());

        boolean existe = this.telefoneRepository.existsByNumTelefoneIn(numTelefones);

        if (existe) {
            throw new BusinessException(BusinessErroEnum.TELEFONE_JA_EXISTE);
        }
    }

}
