package com.totvs.app.repository;

import com.totvs.app.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {

    boolean existsByNumTelefoneIn(List<String> numTelefones);

}
