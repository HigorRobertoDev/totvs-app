package com.totvs.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "CLIENTE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CLIENTE")
    private Integer id;

    @Column(name = "NOME_CLIENTE", length = 10, nullable = false)
    private String nome;

    @Column(name = "ENDERECO_CLIENTE", length = 100, nullable = false)
    private String endereco;

    @Column(name = "BAIRRO_CLIENTE", length = 100, nullable = false)
    private String bairro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Telefone> telefones;

}
