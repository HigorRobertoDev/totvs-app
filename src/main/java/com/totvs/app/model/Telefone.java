package com.totvs.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TELEFONE_CLIENTE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TELEFONE")
    private Integer id;

    @Column(name = "NUMERO_TELEFONE", length = 20, nullable = false)
    private String numTelefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_CLIENTE")
    private Cliente cliente;

}
